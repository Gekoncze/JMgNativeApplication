package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.gui.icons.ImageGallery;

import java.awt.*;


public @Service class ClassImageProvider {
    private final @Mandatory @Shared ApplicationProvider applicationProvider = new ApplicationProvider();

    public @Mandatory Image get(@Optional Class clazz){
        ImageGallery gallery = applicationProvider.get().getImageGallery();
        if(clazz != null){
            Image image = gallery.getImageOptional(getImageName(clazz));
            if(image != null){
                return image;
            } else {
                return gallery.getImage(ImageGallery.UNKNOWN);
            }
        } else {
            return gallery.getImage(ImageGallery.UNKNOWN);
        }
    }

    public @Optional Image getOptional(@Optional Class clazz){
        ImageGallery gallery = applicationProvider.get().getImageGallery();
        if(clazz != null){
            Image image = gallery.getImageOptional(getImageName(clazz));
            if(image != null){
                return image;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private @Mandatory String getImageName(@Mandatory Class clazz){
        return clazz.getSimpleName().toLowerCase() + ".png";
    }
}
