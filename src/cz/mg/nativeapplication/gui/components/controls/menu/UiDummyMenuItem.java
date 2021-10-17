package cz.mg.nativeapplication.gui.components.controls.menu;

import cz.mg.annotations.requirement.Mandatory;

import javax.swing.*;


public class UiDummyMenuItem extends JMenuItem {
    public UiDummyMenuItem(@Mandatory String name) {
        super(name);
        setEnabled(false);
    }
}
