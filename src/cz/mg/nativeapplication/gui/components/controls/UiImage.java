package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;

import java.awt.*;


public @Utility class UiImage extends UiPanel {
    private final @Mandatory @Link Image image;

    public UiImage(@Mandatory Image image) {
        super(0, 0, Alignment.MIDDLE);
        this.image = image;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
