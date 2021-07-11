package cz.mg.nativeapplication.gui.icons;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.map.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


public class IconGallery {
    public static final String MG = "mg.png";
    public static final String PROJECT = "mgproject.png";
    public static final String UNKNOWN = "unknown.png";
    public static final String LIST = "list.png";
    public static final String ATOM = "mgatom.png";
    public static final String FUNCTION = "mgfunction.png";
    public static final String INTERFACE = "mginterface.png";
    public static final String LOCATION = "mglocation.png";
    public static final String STRUCTURE = "mgstructure.png";
    public static final String VARIABLE = "mgvariable.png";

    private final @Mandatory Map<String, Image> images = new Map<>();
    private final @Mandatory Map<String, Icon> icons = new Map<>();

    public IconGallery(){
        load(MG);
        load(PROJECT);
        load(UNKNOWN);
        load(LIST);
        load(ATOM);
        load(FUNCTION);
        load(INTERFACE);
        load(LOCATION);
        load(STRUCTURE);
        load(VARIABLE);
    }

    public @Optional Image getImage(@Mandatory String name){
        return images.get(name);
    }

    public @Optional Icon getIcon(@Mandatory String name){
        return icons.get(name);
    }

    private void load(@Mandatory String name){
        try {
            Image image = ImageIO.read(IconGallery.class.getResourceAsStream(name));
            images.set(name, image);
            icons.set(name, new ImageIcon(image));
        } catch (Exception e) {
            throw new RuntimeException("Could not load icon '" + name + "'.", e);
        }
    }
}
