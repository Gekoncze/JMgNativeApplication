package cz.mg.nativeapplication.sevices.gui;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.map.Map;
import cz.mg.nativeapplication.gui.icons.IconGallery;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public @Service class IconGalleryCreator {
    public @Mandatory IconGallery create(){
        Map<String, Image> images = new Map<>();
        Map<String, Icon> icons = new Map<>();

        for(Field field : IconGallery.class.getFields()){
            if(Modifier.isPublic(field.getModifiers())){
                if(Modifier.isStatic(field.getModifiers())){
                    if(field.getType().equals(String.class)){
                        load(getStringValue(field), images, icons);
                    }
                }
            }
        }

        return new IconGallery(images, icons);
    }

    private @Mandatory String getStringValue(@Mandatory Field field){
        try {
            return (String) field.get(null);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private void load(@Mandatory String name, @Mandatory Map<String, Image> images, @Mandatory Map<String, Icon> icons){
        try {
            Image image = ImageIO.read(IconGallery.class.getResourceAsStream(name));
            images.set(name, image);
            icons.set(name, new ImageIcon(image));
        } catch (Exception e) {
            throw new RuntimeException("Could not load icon '" + name + "'.", e);
        }
    }
}
