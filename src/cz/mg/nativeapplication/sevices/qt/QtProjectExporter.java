package cz.mg.nativeapplication.sevices.qt;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.qt.QtConfig;
import cz.mg.nativeapplication.entities.qt.QtProject;
import cz.mg.nativeapplication.entities.storage.File;


public @Service class QtProjectExporter {
    public File export(QtProject project){
        File file = new File();
        file.name = project.name + ".pro";

        for(QtConfig config : project.settings){
            file.lines.addLast(config.name + " " + operation(config.operation) + " " + config.value);
        }

        return file;
    }

    private String operation(QtConfig.Operation operation){
        switch (operation){
            case ADD: return "+=";
            case REMOVE: return "-=";
            default: throw new NullPointerException();
        }
    }
}
