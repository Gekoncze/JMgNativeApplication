package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.map.Map;
import cz.mg.nativeapplication.gui.icons.ImageGallery;

import javax.imageio.ImageIO;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public @Service class ImageGalleryCreator {
    public @Mandatory ImageGallery create(){
        Map<String, Image> images = new Map<>();

        for(Field field : ImageGallery.class.getFields()){
            if(Modifier.isPublic(field.getModifiers())){
                if(Modifier.isStatic(field.getModifiers())){
                    if(field.getType().equals(String.class)){
                        load(getStringValue(field), images);
                    }
                }
            }
        }

        return new ImageGallery(images);
    }

    private @Mandatory String getStringValue(@Mandatory Field field){
        try {
            return (String) field.get(null);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private void load(@Mandatory String name, @Mandatory Map<String, Image> images){
        try {
            Image image = ImageIO.read(ImageGallery.class.getResourceAsStream(name));
            images.set(name, image);
        } catch (Exception e) {
            throw new RuntimeException("Could not load image '" + name + "'.", e);
        }
    }
}
