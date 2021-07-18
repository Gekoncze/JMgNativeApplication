package cz.mg.nativeapplication.gui;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.entities.mg.MgProject;
import cz.mg.nativeapplication.gui.components.MainMenu;
import cz.mg.nativeapplication.gui.components.MainView;
import cz.mg.nativeapplication.gui.components.RefreshableComponent;
import cz.mg.nativeapplication.gui.handlers.ChangeUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.CloseUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.utilities.NavigationCache;
import cz.mg.nativeapplication.history.History;
import cz.mg.nativeapplication.sevices.gui.NavigationCacheCreator;
import cz.mg.nativeapplication.sevices.mg.creator.MgProjectCreator;
import cz.mg.nativeapplication.sevices.mg.storage.MgProjectLoader;
import cz.mg.nativeapplication.sevices.mg.storage.MgProjectSaver;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.Path;
import java.nio.file.Paths;


public @Utility class MainWindow extends JFrame implements RefreshableComponent {
    private static final String TITLE = "JMgNativeApplication";
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private static final FileFilter FILE_FILTER = new FileNameExtensionFilter("Mg Project Files (*.mg)", "mg");
    private static final int HISTORY_LIMIT = 100;

    private @Optional @Part MgProject project;
    private @Optional @Part Path projectPath;
    private @Optional @Cache NavigationCache navigationCache;
    private @Optional @Part History history;

    private final @Mandatory @Part IconGallery iconGallery;
    private final @Mandatory @Link MainMenu mainMenu;
    private final @Mandatory @Link MainView mainView;

    public MainWindow() {
        iconGallery = new IconGallery();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new CloseUserEventHandler(this, this::exit));
        setTitle(TITLE);
        setIconImage(iconGallery.getImage(IconGallery.MG));
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setJMenuBar(mainMenu = new MainMenu(this));
        setContentPane(mainView = new MainView(this));

        // delete me now
        projectPath = Paths.get("/home/me/Desktop/Dev/Java/JMgNativeApplication/temp/test/Test.mg");
        project = new MgProjectLoader().load(projectPath.toString());
        history = new History(HISTORY_LIMIT);
        refresh();
        // delete me now
    }

    public @Optional MgProject getProject() {
        return project;
    }

    public @Mandatory NavigationCache getNavigationCache() {
        if(navigationCache == null){
            navigationCache = new NavigationCacheCreator().create(project, iconGallery);
        }

        return navigationCache;
    }

    public @Optional History getHistory() {
        return history;
    }

    public @Mandatory IconGallery getIconGallery() {
        return iconGallery;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public MainView getMainView() {
        return mainView;
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
            history = new History(HISTORY_LIMIT);
            refresh();
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
            history = new History(HISTORY_LIMIT);
            refresh();
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

        if(selectedOption == JOptionPane.CANCEL_OPTION){
            return false;
        }

        if(selectedOption == JOptionPane.YES_OPTION){
            if(!saveProject()){
                return false;
            }
        }

        mainView.getMainTabView().closeAllTabs();

        project = null;
        projectPath = null;
        history = null;
        refresh();
        return true;
    }

    public boolean exit(){
        if(!closeProject()){
            return false;
        }

        dispose();
        return true;
    }

    public void showError(Exception e){
        if(e.getMessage() != null && e.getMessage().trim().length() > 0){
            JOptionPane.showMessageDialog(this, e.getMessage(), e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, e.getClass().getSimpleName(), e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        }
        e.printStackTrace();
    }

    @Override
    public void refresh() {
        navigationCache = null;
        getMainView().getProjectTreeView().refresh();
        getMainView().getMainTabView().refresh();
    }

    public static void main(String[] args) {
        new MainWindow().setVisible(true);
    }
}
