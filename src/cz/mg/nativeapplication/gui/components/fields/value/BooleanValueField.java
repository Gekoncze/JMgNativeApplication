package cz.mg.nativeapplication.gui.components.fields.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.fields.ValueField;
import cz.mg.nativeapplication.gui.ui.controls.UiPopupMenu;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiBooleanFieldBase;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiFieldBase;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySelectContent;
import cz.mg.nativeapplication.gui.components.popups.BooleanPopupMenu;


public @Utility class BooleanValueField extends ValueField {
    public BooleanValueField(@Mandatory Object entity, @Mandatory EntityField entityField) {
        super(entity, entityField);
    }

    @Override
    protected @Mandatory UiFieldBase createValueField(@Mandatory EntitySelectContent content) {
        return new UiBooleanFieldBase();
    }

    @Override
    protected @Optional UiPopupMenu createPopupMenu(@Mandatory EntitySelectContent content) {
        return new BooleanPopupMenu(content::setValue);
    }
}