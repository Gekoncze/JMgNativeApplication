package cz.mg.nativeapplication.sevices.gui;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.entities.mg.existing.MgExisting;
import cz.mg.nativeapplication.gui.icons.IconGallery;

import javax.swing.*;


public @Service class ObjectIconProvider {
    public @Optional Icon getIcon(@Optional Object object, @Optional IconGallery iconGallery){
        if(object == null){
            return null;
        }

        if(iconGallery == null){
            return null;
        }

        String name = object instanceof MgExisting
            ? object.getClass().getSuperclass().getSimpleName().toLowerCase() + ".png"
            : object.getClass().getSimpleName().toLowerCase() + ".png";

        Icon icon = iconGallery.getIcon(name);
        if(icon == null){
            icon = iconGallery.getIcon(IconGallery.UNKNOWN);
        }
        return icon;
    }
}
