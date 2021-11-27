package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiText;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySelectContent;
import cz.mg.nativeapplication.gui.components.other.Refreshable;


public abstract @Utility class EntitySelect implements Refreshable {
    public abstract @Mandatory UiText getLabel();
    public abstract @Mandatory EntitySelectContent getContent();
    public abstract @Mandatory List<UiButton> getButtons();
}
