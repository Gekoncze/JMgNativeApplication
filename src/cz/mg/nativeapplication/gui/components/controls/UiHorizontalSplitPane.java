package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;

import javax.swing.*;
import java.awt.*;


public @Utility class UiHorizontalSplitPane extends JSplitPane {
    public UiHorizontalSplitPane(@Mandatory Component first, @Mandatory Component second) {
        super(JSplitPane.HORIZONTAL_SPLIT, first, second);
    }
}
