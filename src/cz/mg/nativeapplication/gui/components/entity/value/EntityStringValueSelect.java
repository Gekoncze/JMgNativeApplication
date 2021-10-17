package cz.mg.nativeapplication.gui.components.entity.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.controls.UiPopupMenu;
import cz.mg.nativeapplication.gui.components.controls.value.UiStringField;
import cz.mg.nativeapplication.gui.components.controls.value.UiValueField;
import cz.mg.nativeapplication.gui.components.entity.EntitySelectType;
import cz.mg.nativeapplication.gui.components.entity.EntityValueSelect;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySelectContent;


public @Utility class EntityStringValueSelect extends EntityValueSelect {
    public EntityStringValueSelect(
        @Mandatory Object entity,
        @Mandatory EntityField entityField,
        @Mandatory EntitySelectType type
    ) {
        super(entity, entityField, type);
    }

    @Override
    protected @Mandatory UiValueField createValueField(@Mandatory EntitySelectContent content) {
        return new UiStringField();
    }

    @Override
    protected @Optional UiPopupMenu createPopupMenu(@Mandatory EntitySelectContent content) {
        return null;
    }
}
