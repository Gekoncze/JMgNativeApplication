package cz.mg.nativeapplication.gui.components.entity.content;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.entity.EntitySelectType;
import cz.mg.nativeapplication.gui.components.other.Refreshable;
import cz.mg.nativeapplication.gui.ui.controls.field.UiField;
import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldBaseFactory;
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

    public static EntitySelectContent create(
        @Mandatory Object entity,
        @Mandatory EntityField entityField,
        @Mandatory EntitySelectType type,
        @Mandatory UiFieldBaseFactory fieldFactory
    ){
        switch (type){
            case SINGLE_SELECT: return new EntitySingleSelectContent(entity, entityField, fieldFactory);
            case MULTI_SELECT: return new EntityMultiSelectContent(entity, entityField, fieldFactory);
            default: throw new UnsupportedOperationException("Unsupported entity select type '" + type + "'.");
        }
    }
}
