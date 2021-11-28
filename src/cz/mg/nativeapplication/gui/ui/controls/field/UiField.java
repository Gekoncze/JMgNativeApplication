package cz.mg.nativeapplication.gui.ui.controls.field;

import cz.mg.annotations.classes.Utility;
import cz.mg.nativeapplication.gui.ui.UiConstants;
import cz.mg.nativeapplication.gui.ui.controls.UiVerticalPanel;

import javax.swing.*;


public abstract @Utility class UiField extends UiVerticalPanel {
    protected static final int BORDER = 2;
    private static final int PADDING = 2;

    public UiField() {
        super(BORDER, PADDING, Alignment.TOP);
        setOpaque(true);
        setBorder(BorderFactory.createEtchedBorder());
        setBackground(UiConstants.getListBackgroundColor());
    }
}
