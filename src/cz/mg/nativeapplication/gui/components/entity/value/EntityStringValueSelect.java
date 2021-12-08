package cz.mg.nativeapplication.gui.components.entity.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.ui.controls.UiPopupMenu;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiFieldBase;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiStringFieldBase;
import cz.mg.nativeapplication.gui.components.entity.EntityValueSelect;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySelectContent;


public @Utility class EntityStringValueSelect extends EntityValueSelect {
    public EntityStringValueSelect(@Mandatory Object entity, @Mandatory EntityField entityField) {
        super(entity, entityField);
    }

    @Override
    protected @Mandatory UiFieldBase createValueField(@Mandatory EntitySelectContent content) {
        return new UiStringFieldBase();
    }

    @Override
    protected @Optional UiPopupMenu createPopupMenu(@Mandatory EntitySelectContent content) {
        return null;
    }
}
