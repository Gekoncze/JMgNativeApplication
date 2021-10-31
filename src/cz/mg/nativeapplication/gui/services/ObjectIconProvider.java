package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;

import javax.swing.*;
import java.awt.*;


public @Service class ObjectIconProvider {
    private final @Mandatory @Shared ClassIconProvider classIconProvider = new ClassIconProvider();

    public @Mandatory Icon get(@Optional Object object){
        if(object != null){
            return classIconProvider.get(object.getClass());
        } else {
            return classIconProvider.get(null);
        }
    }

    public @Optional Image getImageOptional(@Optional Object object){
        if(object != null){
            return classIconProvider.getImageOptional(object.getClass());
        } else {
            return null;
        }
    }
}
