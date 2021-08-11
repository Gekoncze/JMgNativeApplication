package cz.mg.nativeapplication.sevices.gui;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.entities.mg.existing.MgExisting;
import cz.mg.nativeapplication.gui.icons.IconGallery;

import javax.swing.*;


public @Service class ClassIconProvider {
    public @Mandatory Icon get(@Optional Class clazz){
        IconGallery gallery = new IconGalleryProvider().get();
        if(clazz != null){
            Icon icon = gallery.getIcon(getIconName(clazz));
            if(icon != null){
                return icon;
            } else {
                return gallery.getIcon(IconGallery.UNKNOWN);
            }
        } else {
            return gallery.getIcon(IconGallery.UNKNOWN);
        }
    }

    private @Mandatory String getIconName(@Mandatory Class clazz){
        return MgExisting.class.isAssignableFrom(clazz)
            ? clazz.getSuperclass().getSimpleName().toLowerCase() + ".png"
            : clazz.getSimpleName().toLowerCase() + ".png";
    }
}
