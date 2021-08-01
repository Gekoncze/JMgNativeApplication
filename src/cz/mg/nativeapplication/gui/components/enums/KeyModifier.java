package cz.mg.nativeapplication.gui.components.enums;

import cz.mg.annotations.classes.Utility;

import java.awt.event.InputEvent;


public @Utility class KeyModifier {
    public static final int ALT = InputEvent.ALT_DOWN_MASK;
    public static final int CTRL = InputEvent.CTRL_DOWN_MASK;
    public static final int SHIFT = InputEvent.SHIFT_DOWN_MASK;
}
