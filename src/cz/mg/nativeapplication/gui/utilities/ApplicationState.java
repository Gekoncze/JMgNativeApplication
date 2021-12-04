package cz.mg.nativeapplication.gui.utilities;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.mg.entities.MgProject;

import java.nio.file.Path;


public @Utility class ApplicationState {
    private @Optional @Part MgProject project;
    private @Optional @Part Path projectPath;

    public ApplicationState() {
    }

    public @Optional MgProject getProject() {
        return project;
    }

    public void setProject(@Optional MgProject project) {
        this.project = project;
    }

    public @Optional Path getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(@Optional Path projectPath) {
        this.projectPath = projectPath;
    }
}
