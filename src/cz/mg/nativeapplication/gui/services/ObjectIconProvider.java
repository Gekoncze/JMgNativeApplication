package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.gui.icons.IconGallery;

import javax.swing.*;


public @Service class ObjectIconProvider {
    private final @Mandatory @Shared ApplicationProvider applicationProvider = new ApplicationProvider();

    public @Mandatory Icon get(@Optional Object object){
        if(object != null){
            return new ClassIconProvider().get(object.getClass());
        } else {
            return applicationProvider.get().getIconGallery().getIcon(IconGallery.UNKNOWN);
        }
    }
}
