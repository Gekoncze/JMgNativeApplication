package cz.mg.nativeapplication.sevices.gui;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.entities.mg.existing.MgExisting;
import cz.mg.nativeapplication.gui.icons.IconGallery;

import javax.swing.*;


public @Service class ObjectIconProvider {
    public @Mandatory Icon get(@Optional Object object){
        IconGallery gallery = new IconGalleryProvider().get();
        if(object != null){
            Icon icon = gallery.getIcon(getIconName(object));
            if(icon != null){
                return icon;
            } else {
                return gallery.getIcon(IconGallery.UNKNOWN);
            }
        } else {
            return gallery.getIcon(IconGallery.UNKNOWN);
        }
    }

    private @Mandatory String getIconName(@Mandatory Object object){
        return object instanceof MgExisting
            ? object.getClass().getSuperclass().getSimpleName().toLowerCase() + ".png"
            : object.getClass().getSimpleName().toLowerCase() + ".png";
    }
}
