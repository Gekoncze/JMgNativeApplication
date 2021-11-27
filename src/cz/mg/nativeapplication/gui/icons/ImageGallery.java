package cz.mg.nativeapplication.gui.icons;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.map.Map;

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
    public static final String CREATE_ROW = "addRow.png";
    public static final String DELETE_ROW = "removeRow.png";

    public static final String LIST = "list.png";

    public static final String ATOM = "mgatom.png";
    public static final String FUNCTION = "mgfunction.png";
    public static final String INTERFACE = "mginterface.png";
    public static final String LOCATION = "mglocation.png";
    public static final String PROJECT = "mgproject.png";
    public static final String STRUCTURE = "mgstructure.png";
    public static final String VARIABLE = "mgvariable.png";

    private final @Mandatory Map<String, Image> images;

    public ImageGallery(@Mandatory Map<String, Image> images) {
        this.images = images;
    }

    public @Mandatory Image getImage(@Mandatory String name){
        Image image = images.get(name);
        if(image != null){
            return image;
        } else {
            throw new RuntimeException("Missing icon '" + name + "'.");
        }
    }

    public @Optional Image getImageOptional(@Mandatory String name){
        return images.get(name);
    }
}
