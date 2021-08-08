package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.controls.UiTextField;
import cz.mg.nativeapplication.gui.components.other.RefreshableView;


public @Utility interface EntitySelect extends RefreshableView {
    public @Mandatory UiLabel getLabel();
    public @Mandatory UiTextField getContent();
    public @Mandatory List<UiButton> getButtons();
}
