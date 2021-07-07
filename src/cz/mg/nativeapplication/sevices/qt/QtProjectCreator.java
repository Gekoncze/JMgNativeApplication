package cz.mg.nativeapplication.sevices.qt;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CFile;
import cz.mg.nativeapplication.entities.c.CFolder;
import cz.mg.nativeapplication.entities.c.CProject;
import cz.mg.nativeapplication.entities.qt.QtConfig;
import cz.mg.nativeapplication.entities.qt.QtProject;

import static cz.mg.nativeapplication.entities.qt.QtConfig.Operation.ADD;
import static cz.mg.nativeapplication.entities.qt.QtConfig.Operation.REMOVE;
import static cz.mg.nativeapplication.sevices.qt.QtProjectCreator.Option.*;


public @Service class QtProjectCreator {
    public QtProject create(CProject cProject){
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

    private void addFiles(QtProject qtProject, CFolder cFolder){
        for(CFile cFile : cFolder.files){
            Option fileTypeOption = cFile.name.endsWith(".h") ? HEADERS : SOURCES;
            qtProject.settings.addLast(config(fileTypeOption, ADD, cFile.name));
        }

        for(CFolder cSubFolder : cFolder.folders){
            addFiles(qtProject, cSubFolder);
        }
    }

    private QtConfig config(Option option, QtConfig.Operation operation, String value){
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
