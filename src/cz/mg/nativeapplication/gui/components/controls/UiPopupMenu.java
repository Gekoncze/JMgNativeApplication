package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;

import javax.swing.*;


public @Utility class UiPopupMenu extends JPopupMenu implements UiComponent {
    public UiPopupMenu(@Mandatory UiMenuItem... items) {
        for(UiMenuItem item : items){
            add(item);
        }
    }

    public UiPopupMenu(@Mandatory Iterable<UiMenuItem> items) {
        for(UiMenuItem item : items){
            add(item);
        }
    }

    public UiMenuItem getMenuItem(int i) {
        return (UiMenuItem) getComponent(i);
    }
}
