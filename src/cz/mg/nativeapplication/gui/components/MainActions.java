package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.gui.ui.dialogs.UiConfirmDialog;
import cz.mg.nativeapplication.gui.ui.dialogs.UiOpenDialog;
import cz.mg.nativeapplication.gui.ui.dialogs.UiSaveDialog;
import cz.mg.nativeapplication.gui.services.ApplicationProvider;
import cz.mg.nativeapplication.gui.utilities.FileFilters;

import javax.swing.*;
import java.nio.file.Path;


public @Utility class MainActions {
    private final @Mandatory @Shared ApplicationProvider applicationProvider = new ApplicationProvider();

    public MainActions() {
    }

    // FILE

    public boolean newProject(){
        if(applicationProvider.get().getApplicationState().getProject() != null){
            if(!closeProject()){
                return false;
            }
        }

        String name = JOptionPane.showInputDialog(this, "New Project");
        if(name != null){
            applicationProvider.get().getApplicationState().newProject(name);
            refresh();
            return true;
        } else {
            return false;
        }
    }

    public boolean openProject(){
        Path selectedPath = new UiOpenDialog("Open project", FileFilters.MG).show();

        if(selectedPath != null){
            if(applicationProvider.get().getApplicationState().getProject() != null){
                if(!closeProject()){
                    return false;
                }
            }

            applicationProvider.get().getApplicationState().openProject(selectedPath.toAbsolutePath());
            refresh();
            return true;
        } else {
            return false;
        }
    }

    public boolean saveProject(){
        if(applicationProvider.get().getApplicationState().getProject() == null){
            return false;
        }

        if(applicationProvider.get().getApplicationState().getProjectPath() != null){
            applicationProvider.get().getApplicationState().saveProject();
            return true;
        } else {
            return saveProjectAs();
        }
    }

    public boolean saveProjectAs(){
        if(applicationProvider.get().getApplicationState().getProject() == null){
            return false;
        }

        Path selectedPath = new UiSaveDialog("Save project", FileFilters.MG).show();

        if(selectedPath != null){
            applicationProvider.get().getApplicationState().saveProjectAs(selectedPath.toAbsolutePath());
            return true;
        } else {
            return false;
        }
    }

    public boolean closeProject(){
        if(applicationProvider.get().getApplicationState().getProject() == null){
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

        applicationProvider.get().getMainWindow().getMainView().getMainTabView().closeAllTabs();

        applicationProvider.get().getApplicationState().closeProject();
        refresh();
        return true;
    }

    public boolean exit(){
        if(!closeProject()){
            return false;
        }

        applicationProvider.get().getMainWindow().dispose();
        return true;
    }

    // EDIT

    public void undo(){
        applicationProvider.get().getApplicationState().getTransactionManager().getHistory().undo();
        applicationProvider.get().getMainWindow().refresh();
    }

    public void redo(){
        applicationProvider.get().getApplicationState().getTransactionManager().getHistory().redo();
        applicationProvider.get().getMainWindow().refresh();
    }

    // VIEW

    public void refresh(){
        applicationProvider.get().getMainWindow().refresh();
    }

    public void closeActiveTab(){
        applicationProvider.get().getMainWindow().getMainView().getMainTabView().closeActiveTab();
    }

    public void selectNextTab(){
        applicationProvider.get().getMainWindow().getMainView().getMainTabView().selectNextTab();
    }

    public void selectPreviousTab(){
        applicationProvider.get().getMainWindow().getMainView().getMainTabView().selectPreviousTab();
    }
}
