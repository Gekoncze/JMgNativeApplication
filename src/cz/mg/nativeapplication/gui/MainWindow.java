package cz.mg.nativeapplication.gui;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.mg.MgProject;
import cz.mg.nativeapplication.gui.components.MainMenu;
import cz.mg.nativeapplication.gui.components.MainView;
import cz.mg.nativeapplication.gui.handlers.CloseUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.utilities.NavigationCache;
import cz.mg.nativeapplication.sevices.gui.NavigationCacheCreator;
import cz.mg.nativeapplication.sevices.mg.creator.MgProjectCreator;
import cz.mg.nativeapplication.sevices.mg.storage.MgProjectLoader;
import cz.mg.nativeapplication.sevices.mg.storage.MgProjectSaver;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.Path;
import java.nio.file.Paths;


public @Utility class MainWindow extends JFrame {
    private static final String TITLE = "JMgNativeApplication";
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private static final FileFilter FILE_FILTER = new FileNameExtensionFilter("Mg Project Files (*.mg)", "mg");

    private @Optional @Part MgProject project;
    private @Optional @Part Path projectPath;
    private @Optional @Cache NavigationCache navigationCache;
    private @Mandatory @Part IconGallery iconGallery;

    private final @Mandatory List<ChangeListener> changeListeners = new List<>();

    public MainWindow() {
        iconGallery = new IconGallery();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new CloseUserEventHandler(this, this::exit));
        setTitle(TITLE);
        setIconImage(iconGallery.getImage(IconGallery.MG));
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setJMenuBar(new MainMenu(this));
        setContentPane(new MainView(this));

        // delete me now
        projectPath = Paths.get("/home/me/Desktop/Dev/Java/JMgNativeApplication/temp/test/Test.mg");
        project = new MgProjectLoader().load(projectPath.toString());
        notifyChangeListeners(project);
        // delete me now
    }

    public @Optional MgProject getProject() {
        return project;
    }

    public @Mandatory NavigationCache getNavigationCache() {
        if(navigationCache == null){
            navigationCache = new NavigationCacheCreator().create(project);
        }

        return navigationCache;
    }

    public @Mandatory IconGallery getIconGallery() {
        return iconGallery;
    }

    public void addChangeListener(@Mandatory ChangeListener changeListener){
        changeListeners.addLast(changeListener);
    }

    private void notifyChangeListeners(@Optional Object source){
        navigationCache = null;
        ChangeEvent event = new ChangeEvent(source != null ? source : new Object());
        for(ChangeListener changeListener : changeListeners){
            changeListener.stateChanged(event);
        }
    }

    public boolean newProject(){
        if(project != null){
            if(!closeProject()){
                return false;
            }
        }

        String name = JOptionPane.showInputDialog(this, "New Project");
        if(name != null){
            project = new MgProjectCreator().create(name);
            notifyChangeListeners(project);
            return true;
        } else {
            return false;
        }
    }

    public boolean openProject(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open project");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(FILE_FILTER);
        fileChooser.setFileFilter(FILE_FILTER);

        fileChooser.showOpenDialog(this);

        if(fileChooser.getSelectedFile() != null){
            if(project != null){
                if(!closeProject()){
                    return false;
                }
            }

            projectPath = fileChooser.getSelectedFile().toPath().toAbsolutePath();
            project = new MgProjectLoader().load(projectPath.toString());
            notifyChangeListeners(project);
            return true;
        } else {
            return false;
        }
    }

    public boolean saveProject(){
        if(project == null){
            return false;
        }

        if(projectPath != null){
            new MgProjectSaver().save(projectPath.toString(), project);
            return true;
        } else {
            return saveProjectAs();
        }
    }

    public boolean saveProjectAs(){
        if(project == null){
            return false;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save project");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(FILE_FILTER);
        fileChooser.setFileFilter(FILE_FILTER);

        fileChooser.showSaveDialog(this);

        if(fileChooser.getSelectedFile() != null){
            projectPath = fileChooser.getSelectedFile().toPath().toAbsolutePath();
            new MgProjectSaver().save(projectPath.toString(), project);
            return true;
        } else {
            return false;
        }
    }

    public boolean closeProject(){
        if(project == null){
            return true;
        }

        int selectedOption = JOptionPane.showConfirmDialog(
            this,
            "Would you like to save project before closing?",
            "Close project",
            JOptionPane.YES_NO_CANCEL_OPTION
        );

        if(selectedOption == JOptionPane.YES_OPTION){
            if(!saveProject()){
                return false;
            }
            project = null;
            projectPath = null;
            notifyChangeListeners(project);
            return true;
        } else if(selectedOption == JOptionPane.NO_OPTION){
            project = null;
            projectPath = null;
            notifyChangeListeners(project);
            return true;
        } else {
            return false;
        }
    }

    public boolean exit(){
        if(!closeProject()){
            return false;
        }

        dispose();
        return true;
    }

    public void showError(Exception e){
        JOptionPane.showMessageDialog(this, e.getMessage(), e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new MainWindow().setVisible(true);
    }
}
