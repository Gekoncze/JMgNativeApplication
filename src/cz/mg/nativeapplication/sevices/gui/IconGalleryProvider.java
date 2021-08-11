package cz.mg.nativeapplication.sevices.gui;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.icons.IconGallery;


public @Service class IconGalleryProvider {
    private static @Optional @Part IconGallery instance;

    private synchronized static @Mandatory IconGallery getInstance(){
        if(instance == null){
            instance = new IconGalleryCreator().create();
        }

        return instance;
    }

    public @Mandatory IconGallery get(){
        return getInstance();
    }
}
