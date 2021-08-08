package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiComponent;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.other.RefreshableView;


public @Utility abstract class EntitySelect implements RefreshableView {
    public abstract @Mandatory UiLabel getLabel();
    public abstract @Mandatory UiComponent getContent();
    public abstract @Mandatory List<UiButton> getButtons();
}
