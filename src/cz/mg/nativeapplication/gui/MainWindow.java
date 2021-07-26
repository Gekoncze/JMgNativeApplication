package cz.mg.nativeapplication.gui;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.components.MainMenu;
import cz.mg.nativeapplication.gui.components.MainView;
import cz.mg.nativeapplication.gui.components.RefreshableView;
import cz.mg.nativeapplication.gui.components.dialogs.UiConfirmDialog;
import cz.mg.nativeapplication.gui.components.dialogs.UiErrorMessageDialog;
import cz.mg.nativeapplication.gui.components.dialogs.UiOpenDialog;
import cz.mg.nativeapplication.gui.components.dialogs.UiSaveDialog;
import cz.mg.nativeapplication.gui.handlers.CloseUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.other.ApplicationState;
import cz.mg.nativeapplication.gui.other.FileFilters;
import cz.mg.nativeapplication.gui.other.NavigationCache;
import cz.mg.nativeapplication.sevices.gui.NavigationCacheCreator;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.util.HashSet;


public @Utility class MainWindow extends JFrame implements RefreshableView {
    private static final String TITLE = "JMgNativeApplication";
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    private final @Mandatory @Part ApplicationState applicationState = new ApplicationState();
    private final @Mandatory @Part IconGallery iconGallery = new IconGallery();
    private final @Mandatory @Link MainMenu mainMenu;
    private final @Mandatory @Link MainView mainView;

    private @Optional @Cache NavigationCache navigationCache;

    public MainWindow() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new CloseUserEventHandler(this, this::exit));
        setTitle(TITLE);
        setIconImage(iconGallery.getImage(IconGallery.MG));
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setJMenuBar(mainMenu = new MainMenu(this));
        setContentPane(mainView = new MainView(this));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, new HashSet<>());
        setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, new HashSet<>());
        refresh();
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public @Mandatory NavigationCache getNavigationCache() {
        if(navigationCache == null){
            navigationCache = new NavigationCacheCreator().create(applicationState.getProject(), iconGallery);
        }

        return navigationCache;
    }

    public @Mandatory IconGallery getIconGallery() {
        return iconGallery;
    }

    public @Mandatory MainMenu getMainMenu() {
        return mainMenu;
    }

    public @Mandatory MainView getMainView() {
        return mainView;
    }

    public boolean newProject(){
        if(applicationState.getProject() != null){
            if(!closeProject()){
                return false;
            }
        }

        String name = JOptionPane.showInputDialog(this, "New Project");
        if(name != null){
            applicationState.newProject(name);
            refresh();
            return true;
        } else {
            return false;
        }
    }

    public boolean openProject(){
        Path selectedPath = new UiOpenDialog("Open project", FileFilters.MG).show();

        if(selectedPath != null){
            if(applicationState.getProject() != null){
                if(!closeProject()){
                    return false;
                }
            }

            applicationState.openProject(selectedPath.toAbsolutePath());
            refresh();
            return true;
        } else {
            return false;
        }
    }

    public boolean saveProject(){
        if(applicationState.getProject() == null){
            return false;
        }

        if(applicationState.getProjectPath() != null){
            applicationState.saveProject();
            return true;
        } else {
            return saveProjectAs();
        }
    }

    public boolean saveProjectAs(){
        if(applicationState.getProject() == null){
            return false;
        }

        Path selectedPath = new UiSaveDialog("Save project", FileFilters.MG).show();

        if(selectedPath != null){
            applicationState.saveProjectAs(selectedPath.toAbsolutePath());
            return true;
        } else {
            return false;
        }
    }

    public boolean closeProject(){
        if(applicationState.getProject() == null){
            return true;
        }

        UiConfirmDialog.Choice choice = new UiConfirmDialog(
            "Close project",
            "Would you like to save project before closing?"
        ).show();

        if(choice == UiConfirmDialog.Choice.CANCEL){
            return false;
        }

        if(choice == UiConfirmDialog.Choice.YES){
            if(!saveProject()){
                return false;
            }
        }

        mainView.getMainTabView().closeAllTabs();

        applicationState.closeProject();
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
        e.printStackTrace();
        new UiErrorMessageDialog(e).show();
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
