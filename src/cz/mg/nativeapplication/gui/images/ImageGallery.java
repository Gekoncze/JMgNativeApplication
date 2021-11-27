package cz.mg.nativeapplication.gui.images;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.map.Map;

import javax.imageio.ImageIO;
import java.awt.*;


public class ImageGallery {
    public static final String MG = "mg.png";
    public static final String UNKNOWN = "unknown.png";

    public static final String CLEAR = "clear.png";
    public static final String CREATE = "create.png";
    public static final String EDIT = "edit.png";
    public static final String DELETE = "delete.png";
    public static final String DOWN = "down.png";
    public static final String UP = "up.png";
    public static final String SEARCH = "search.png";
    public static final String OPEN = "open.png";
    public static final String CREATE_ROW = "addrow.png";
    public static final String DELETE_ROW = "removerow.png";

    private final @Mandatory Map<String, Image> images = new Map<>();

    public ImageGallery() {
    }

    public @Mandatory Image getImage(@Mandatory String name){
        Image image = getImageOptional(name);
        if(image != null){
            return image;
        } else {
            throw new RuntimeException("Missing image '" + name + "'.");
        }
    }

    public @Optional Image getImageOptional(@Mandatory String name){
        update(name);
        return images.get(name);
    }

    private void update(@Mandatory String name){
        if(!images.containsKey(name)){
            images.set(name, load(name));
        }
    }

    private @Optional Image load(@Mandatory String name){
        try {
            return ImageIO.read(ImageGallery.class.getResourceAsStream(name));
        } catch (Exception e) {
            return null;
        }
    }
}
