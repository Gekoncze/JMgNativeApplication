package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.Application;


public @Service class ApplicationProvider {
    private static @Optional Application application;

    public void set(@Optional Application application) {
        ApplicationProvider.application = application;
    }

    public @Mandatory Application get(){
        if(application != null){
            return application;
        } else {
            throw new IllegalStateException("Missing application.");
        }
    }
}
