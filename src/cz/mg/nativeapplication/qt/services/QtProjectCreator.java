package cz.mg.nativeapplication.qt.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.c.entities.CFile;
import cz.mg.nativeapplication.c.entities.CFolder;
import cz.mg.nativeapplication.c.entities.CProject;
import cz.mg.nativeapplication.qt.entities.QtConfig;
import cz.mg.nativeapplication.qt.entities.QtProject;

import static cz.mg.nativeapplication.qt.entities.QtConfig.Operation.ADD;
import static cz.mg.nativeapplication.qt.entities.QtConfig.Operation.REMOVE;
import static cz.mg.nativeapplication.qt.services.QtProjectCreator.Option.*;


public @Service class QtProjectCreator {
    public @Mandatory QtProject create(@Mandatory CProject cProject){
        QtProject qtProject = new QtProject();
        qtProject.name = cProject.name;

        qtProject.settings.addLast(config(TEMPLATE, ADD, cProject.library ? "lib" : "app"));
        qtProject.settings.addLast(config(CONFIG, REMOVE, "app_bundle"));
        qtProject.settings.addLast(config(CONFIG, REMOVE, "qt"));
        qtProject.settings.addLast(config(INCLUDEPATH, ADD, "/usr/lib/gcc/x86_64-linux-gnu/9/include"));
        qtProject.settings.addLast(config(QMAKE_CFLAGS, ADD, "-std=c11 -Wall -Wextra -Wpedantic -Werror"));

        for(String library : cProject.libraries){
            qtProject.settings.addLast(config(LIBS, ADD, "-l" + library));
        }

        addFiles(qtProject, cProject.root);

        return qtProject;
    }

    private void addFiles(@Mandatory QtProject qtProject, @Mandatory CFolder cFolder){
        for(CFile cFile : cFolder.files){
            Option fileTypeOption = cFile.name.endsWith(".h") ? HEADERS : SOURCES;
            qtProject.settings.addLast(config(fileTypeOption, ADD, cFile.name));
        }

        for(CFolder cSubFolder : cFolder.folders){
            addFiles(qtProject, cSubFolder);
        }
    }

    private QtConfig config(@Mandatory Option option, @Mandatory QtConfig.Operation operation, @Mandatory String value){
        return new QtConfig(option.name(), operation, value);
    }

    protected enum Option {
        TEMPLATE,
        CONFIG,
        INCLUDEPATH,
        QMAKE_CFLAGS,
        LIBS,
        HEADERS,
        SOURCES
    }
}
