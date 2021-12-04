package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.explorer.history.TransactionManager;
import cz.mg.nativeapplication.gui.utilities.ApplicationState;
import cz.mg.nativeapplication.mg.services.creator.MgProjectCreator;
import cz.mg.nativeapplication.mg.services.storage.MgProjectLoader;
import cz.mg.nativeapplication.mg.services.storage.MgProjectSaver;

import java.nio.file.Path;


public @Mandatory class ApplicationService {
    private final @Mandatory @Shared MgProjectSaver projectSaver = new MgProjectSaver();
    private final @Mandatory @Shared MgProjectLoader projectLoader = new MgProjectLoader();

    public void newProject(
        @Mandatory TransactionManager transactionManager,
        @Mandatory ApplicationState applicationState,
        @Mandatory String name
    ) {
        transactionManager.getHistory().clear();
        applicationState.setProject(new MgProjectCreator().create(name));
        applicationState.setProjectPath(null);
    }

    public void openProject(
        @Mandatory TransactionManager transactionManager,
        @Mandatory ApplicationState applicationState,
        @Mandatory Path path
    ) {
        transactionManager.getHistory().clear();
        applicationState.setProject(projectLoader.load(path));
        applicationState.setProjectPath(path);
    }

    public void saveProject(@Mandatory ApplicationState applicationState) {
        if(applicationState.getProjectPath() != null){
            projectSaver.save(
                applicationState.getProjectPath(),
                applicationState.getProject()
            );
        } else {
            throw new RuntimeException("Missing path to project file.");
        }
    }

    public void saveProjectAs(@Mandatory ApplicationState applicationState, @Mandatory Path path) {
        applicationState.setProjectPath(path);
        saveProject(applicationState);
    }

    public void closeProject(
        @Mandatory TransactionManager transactionManager,
        @Mandatory ApplicationState applicationState
    ) {
        transactionManager.getHistory().clear();
        applicationState.setProject(null);
        applicationState.setProjectPath(null);
    }
}
