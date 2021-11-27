package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;

import java.awt.*;


public @Service class ObjectImageProvider {
    private final @Mandatory @Shared ClassImageProvider classImageProvider = new ClassImageProvider();

    public @Mandatory Image get(@Optional Object object){
        if(object != null){
            return classImageProvider.get(object.getClass());
        } else {
            return classImageProvider.get(null);
        }
    }

    public @Optional Image getOptional(@Optional Object object){
        if(object != null){
            return classImageProvider.getOptional(object.getClass());
        } else {
            return null;
        }
    }
}
