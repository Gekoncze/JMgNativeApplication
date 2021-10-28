package cz.mg.nativeapplication.gui;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.services.creator.MgProjectCreator;
import cz.mg.nativeapplication.mg.services.history.TransactionManager;
import cz.mg.nativeapplication.mg.services.storage.MgProjectLoader;
import cz.mg.nativeapplication.mg.services.storage.MgProjectSaver;

import java.nio.file.Path;


public @Utility class ApplicationState {
    private @Mandatory @Part TransactionManager transactionManager = new TransactionManager();
    private @Optional @Part MgProject project;
    private @Optional @Part Path projectPath;

    public ApplicationState() {
    }

    public @Mandatory TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public @Optional MgProject getProject() {
        return project;
    }

    public @Optional Path getProjectPath() {
        return projectPath;
    }

    public void newProject(@Mandatory String name) {
        transactionManager.getHistory().clear();
        project = new MgProjectCreator().create(name);
        projectPath = null;
    }

    public void openProject(@Mandatory Path path) {
        transactionManager.getHistory().clear();
        project = new MgProjectLoader().load(path);
        projectPath = path;
    }

    public void saveProject() {
        new MgProjectSaver().save(projectPath, project);
    }

    public void saveProjectAs(@Mandatory Path path) {
        projectPath = path;
        saveProject();
    }

    public void closeProject() {
        transactionManager.getHistory().clear();
        project = null;
        projectPath = null;
    }
}
