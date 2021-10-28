package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.Application;


public @Service class ApplicationProvider {
    private static @Optional Application instance;

    public void set(@Optional Application instance) {
        ApplicationProvider.instance = instance;
    }

    public @Mandatory Application get(){
        if(instance != null){
            return instance;
        } else {
            throw new IllegalStateException("Missing application.");
        }
    }
}
