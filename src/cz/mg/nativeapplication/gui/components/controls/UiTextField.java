package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;

import javax.swing.*;


public @Utility class UiTextField extends JTextField {
    public UiTextField() {
        setBackground(UIManager.getDefaults().getColor("TextField.background"));
        setBorder(BorderFactory.createEtchedBorder());
    }
}
