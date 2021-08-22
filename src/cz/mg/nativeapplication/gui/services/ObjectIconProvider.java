package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.icons.IconGallery;

import javax.swing.*;


public @Service class ObjectIconProvider {
    IconGallery gallery = new IconGalleryProvider().get();
    public @Mandatory Icon get(@Optional Object object){
        if(object != null){
            return new ClassIconProvider().get(object.getClass());
        } else {
            return gallery.getIcon(IconGallery.UNKNOWN);
        }
    }
}
