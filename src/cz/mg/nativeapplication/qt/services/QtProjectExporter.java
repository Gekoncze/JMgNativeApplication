package cz.mg.nativeapplication.qt.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.qt.entities.QtConfig;
import cz.mg.nativeapplication.qt.entities.QtProject;
import cz.mg.nativeapplication.storage.entities.File;


public @Service class QtProjectExporter {
    public @Mandatory File export(@Mandatory QtProject project){
        File file = new File();
        file.name = project.name + ".pro";

        for(QtConfig config : project.settings){
            file.lines.addLast(config.name + " " + operation(config.operation) + " " + config.value);
        }

        return file;
    }

    private @Mandatory String operation(@Mandatory QtConfig.Operation operation){
        switch (operation){
            case ADD: return "+=";
            case REMOVE: return "-=";
            default: throw new IllegalArgumentException("Unknown qt operation " + operation + ".");
        }
    }
}
