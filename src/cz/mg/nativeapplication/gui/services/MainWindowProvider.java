package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.components.ExplorerWindow;


public @Service class MainWindowProvider {
    private static @Optional ExplorerWindow instance;

    public void set(@Mandatory ExplorerWindow instance) {
        MainWindowProvider.instance = instance;
    }

    public @Mandatory ExplorerWindow get(){
        if(instance != null){
            return instance;
        } else {
            throw new IllegalStateException("Missing main window.");
        }
    }
}
