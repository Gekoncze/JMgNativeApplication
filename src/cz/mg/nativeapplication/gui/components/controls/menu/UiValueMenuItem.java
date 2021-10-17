package cz.mg.nativeapplication.gui.components.controls.menu;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;

import javax.swing.*;


public @Utility class UiValueMenuItem<T> extends UiMenuItem {
    public UiValueMenuItem(
        @Mandatory T value,
        @Mandatory String name,
        @Mandatory SelectEventHandler<T> handler
    ) {
        this(null, value, name, handler);
    }

    public UiValueMenuItem(
        @Optional Icon icon,
        @Mandatory T value,
        @Mandatory String name,
        @Mandatory SelectEventHandler<T> handler
    ) {
        super(icon, name, () -> handler.select(value));
    }

    public interface SelectEventHandler<T> {
        public void select(@Mandatory T value);
    }
}
