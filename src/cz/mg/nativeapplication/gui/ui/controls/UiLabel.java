package cz.mg.nativeapplication.gui.ui.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;

import java.awt.*;
import java.awt.event.MouseListener;

import static cz.mg.nativeapplication.gui.ui.controls.UiPanel.Alignment.LEFT;
import static cz.mg.nativeapplication.gui.ui.controls.UiPanel.Alignment.MIDDLE;
import static cz.mg.nativeapplication.gui.ui.controls.UiPanel.Fill.BOTH;
import static cz.mg.nativeapplication.gui.ui.controls.UiPanel.Fill.NONE;


public @Utility class UiLabel extends UiPanel implements UiComponent {
    private static final int PADDING = 4;

    private final @Mandatory @Shared UiImage imageComponent;
    private final @Mandatory @Shared UiText textComponent;

    public UiLabel(@Mandatory Image image, @Mandatory String text) {
        super(0, PADDING, LEFT);

        imageComponent = new UiImage(image);
        add(imageComponent, 0, 0, 0, 0, MIDDLE, BOTH);

        textComponent = new UiText(text, UiText.FontStyle.BOLD);
        add(textComponent, 1, 0, 0, 0, MIDDLE, NONE);

        imageComponent.setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));

        rebuild();
    }

    @Override
    public void addMouseListener(@Mandatory MouseListener listener) {
        super.addMouseListener(listener);
        imageComponent.addMouseListener(listener);
        textComponent.addMouseListener(listener);
    }
}
