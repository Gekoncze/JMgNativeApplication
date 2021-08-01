package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.gui.components.dialogs.UiConfirmDialog;
import cz.mg.nativeapplication.gui.components.dialogs.UiOpenDialog;
import cz.mg.nativeapplication.gui.components.dialogs.UiSaveDialog;
import cz.mg.nativeapplication.gui.other.FileFilters;

import javax.swing.*;
import java.nio.file.Path;


public @Utility class MainActions {
    private final @Mandatory @Link MainWindow mainWindow;

    public MainActions(@Mandatory MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    // FILE

    public boolean newProject(){
        if(mainWindow.getApplicationState().getProject() != null){
            if(!closeProject()){
                return false;
            }
        }

        String name = JOptionPane.showInputDialog(this, "New Project");
        if(name != null){
            mainWindow.getApplicationState().newProject(name);
            refresh();
            return true;
        } else {
            return false;
        }
    }

    public boolean openProject(){
        Path selectedPath = new UiOpenDialog("Open project", FileFilters.MG).show();

        if(selectedPath != null){
            if(mainWindow.getApplicationState().getProject() != null){
                if(!closeProject()){
                    return false;
                }
            }

            mainWindow.getApplicationState().openProject(selectedPath.toAbsolutePath());
            refresh();
            return true;
        } else {
            return false;
        }
    }

    public boolean saveProject(){
        if(mainWindow.getApplicationState().getProject() == null){
            return false;
        }

        if(mainWindow.getApplicationState().getProjectPath() != null){
            mainWindow.getApplicationState().saveProject();
            return true;
        } else {
            return saveProjectAs();
        }
    }

    public boolean saveProjectAs(){
        if(mainWindow.getApplicationState().getProject() == null){
            return false;
        }

        Path selectedPath = new UiSaveDialog("Save project", FileFilters.MG).show();

        if(selectedPath != null){
            mainWindow.getApplicationState().saveProjectAs(selectedPath.toAbsolutePath());
            return true;
        } else {
            return false;
        }
    }

    public boolean closeProject(){
        if(mainWindow.getApplicationState().getProject() == null){
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

        mainWindow.getMainView().getMainTabView().closeAllTabs();

        mainWindow.getApplicationState().closeProject();
        refresh();
        return true;
    }

    public boolean exit(){
        if(!closeProject()){
            return false;
        }

        mainWindow.dispose();
        return true;
    }

    // EDIT

    public void undo(){
        if(mainWindow.getApplicationState().getHistory() != null){
            mainWindow.getApplicationState().getHistory().undo();
            mainWindow.refresh();
        }
    }

    public void redo(){
        if(mainWindow.getApplicationState().getHistory() != null){
            mainWindow.getApplicationState().getHistory().redo();
            mainWindow.refresh();
        }
    }

    // VIEW

    public void refresh(){
        mainWindow.refresh();
    }

    public void closeActiveTab(){
        mainWindow.getMainView().getMainTabView().closeActiveTab();
    }

    public void selectNextTab(){
        mainWindow.getMainView().getMainTabView().selectNextTab();
    }

    public void selectPreviousTab(){
        mainWindow.getMainView().getMainTabView().selectPreviousTab();
    }
}
