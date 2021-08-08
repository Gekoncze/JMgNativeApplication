package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;

import javax.swing.*;


public @Utility class UiMenuBar extends JMenuBar implements UiComponent {
    public UiMenuBar(@Mandatory UiMenu... menus) {
        for(UiMenu menu : menus){
            add(menu);
        }
    }
}
