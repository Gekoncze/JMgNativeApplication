package cz.mg.nativeapplication.gui.components.entity.content;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.components.other.Refreshable;
import cz.mg.nativeapplication.gui.ui.controls.field.UiField;
import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldBaseWrapper;


public abstract @Utility class EntitySelectContent implements Refreshable {
    public abstract @Mandatory Object getParent();
    public abstract @Optional Integer getChildIndex();
    public abstract @Mandatory Class getType();
    public abstract @Mandatory String getName();
    public abstract @Optional Object getValue();
    public abstract void setValue(@Optional Object value);
    public abstract @Optional UiFieldBaseWrapper getFieldBase();
    public abstract @Mandatory UiField getField();
    public abstract void softRefresh();
}
