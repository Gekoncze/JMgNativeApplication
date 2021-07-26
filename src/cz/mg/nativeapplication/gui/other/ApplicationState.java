package cz.mg.nativeapplication.gui.other;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.entities.mg.MgProject;
import cz.mg.nativeapplication.history.History;
import cz.mg.nativeapplication.sevices.mg.creator.MgProjectCreator;
import cz.mg.nativeapplication.sevices.mg.storage.MgProjectLoader;
import cz.mg.nativeapplication.sevices.mg.storage.MgProjectSaver;

import java.nio.file.Path;


public @Utility class ApplicationState {
    private static final int HISTORY_LIMIT = 100;

    private @Optional @Part MgProject project;
    private @Optional @Part Path projectPath;
    private @Optional @Part History history;

    public ApplicationState() {
    }

    public @Optional MgProject getProject() {
        return project;
    }

    public @Optional Path getProjectPath() {
        return projectPath;
    }

    public @Optional History getHistory() {
        return history;
    }

    public void newProject(@Mandatory String name) {
        project = new MgProjectCreator().create(name);
        projectPath = null;
        history = new History(HISTORY_LIMIT);
    }

    public void openProject(@Mandatory Path path) {
        projectPath = path;
        project = new MgProjectLoader().load(projectPath);
        history = new History(HISTORY_LIMIT);
    }

    public void saveProject() {
        new MgProjectSaver().save(projectPath, project);
    }

    public void saveProjectAs(@Mandatory Path path) {
        projectPath = path;
        saveProject();
    }

    public void closeProject() {
        project = null;
        projectPath = null;
        history = null;
    }
}
