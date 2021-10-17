package cz.mg.nativeapplication.gui.components.entity.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.controls.UiPopupMenu;
import cz.mg.nativeapplication.gui.components.controls.value.UiBooleanField;
import cz.mg.nativeapplication.gui.components.controls.value.UiValueField;
import cz.mg.nativeapplication.gui.components.entity.EntitySelectType;
import cz.mg.nativeapplication.gui.components.entity.EntityValueSelect;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySelectContent;
import cz.mg.nativeapplication.gui.components.entity.popups.BooleanPopupMenu;


public @Utility class EntityBooleanValueSelect extends EntityValueSelect {
    public EntityBooleanValueSelect(
        @Mandatory Object entity,
        @Mandatory EntityField entityField,
        @Mandatory EntitySelectType type
    ) {
        super(entity, entityField, type);
    }

    @Override
    protected @Mandatory UiValueField createValueField(@Mandatory EntitySelectContent content) {
        return new UiBooleanField();
    }

    @Override
    protected @Optional UiPopupMenu createPopupMenu(@Mandatory EntitySelectContent content) {
        return new BooleanPopupMenu(content::setValue);
    }
}
