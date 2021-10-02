package cz.mg.nativeapplication.gui.icons;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.map.Map;

import javax.swing.*;
import java.awt.*;


public class IconGallery {
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

    public static final String LIST = "list.png";

    public static final String ATOM = "mgatom.png";
    public static final String FUNCTION = "mgfunction.png";
    public static final String INTERFACE = "mginterface.png";
    public static final String LOCATION = "mglocation.png";
    public static final String PROJECT = "mgproject.png";
    public static final String STRUCTURE = "mgstructure.png";
    public static final String VARIABLE = "mgvariable.png";

    private final @Mandatory Map<String, Image> images;
    private final @Mandatory Map<String, Icon> icons;

    public IconGallery(@Mandatory Map<String, Image> images, @Mandatory Map<String, Icon> icons) {
        this.images = images;
        this.icons = icons;
    }

    public @Optional Image getImage(@Mandatory String name){
        return images.get(name);
    }

    public @Optional Icon getIcon(@Mandatory String name){
        return icons.get(name);
    }
}
