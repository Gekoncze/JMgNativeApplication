package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.explorer.history.TransactionManager;
import cz.mg.nativeapplication.gui.utilities.ApplicationState;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.services.creator.MgProjectCreator;
import cz.mg.nativeapplication.mg.services.storage.EntityReader;
import cz.mg.nativeapplication.mg.services.storage.EntityWriter;

import java.nio.file.Path;


public @Mandatory class ApplicationService {
    private final @Mandatory @Shared EntityWriter entityWriter = new EntityWriter();
    private final @Mandatory @Shared EntityReader entityReader = new EntityReader();

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
        applicationState.setProject((MgProject) entityReader.read(path.toString()));
        applicationState.setProjectPath(path);
    }

    public void saveProject(@Mandatory ApplicationState applicationState) {
        if(applicationState.getProjectPath() != null){
            entityWriter.write(
                applicationState.getProjectPath().toString(),
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
