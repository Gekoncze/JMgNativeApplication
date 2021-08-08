package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.gui.components.other.RefreshableView;

import java.awt.*;


public @Utility interface EntitySelect extends RefreshableView {
    public @Mandatory Component getLabel();
    public @Mandatory Component getContent();
    public @Mandatory Component getButtons();
}
