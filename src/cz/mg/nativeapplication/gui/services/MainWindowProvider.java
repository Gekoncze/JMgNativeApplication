package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.components.MainWindow;


public @Service class MainWindowProvider {
    private static @Optional MainWindow instance;

    public void set(@Mandatory MainWindow instance) {
        MainWindowProvider.instance = instance;
    }

    public @Mandatory MainWindow get(){
        if(instance != null){
            return instance;
        } else {
            throw new IllegalStateException("Missing main window.");
        }
    }
}
