package cz.mg.nativeapplication.gui.components.fields.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.explorer.Explorer;
import cz.mg.nativeapplication.gui.components.fields.ValueField;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiStringFieldBase;


public @Utility class StringValueField extends ValueField {
    public StringValueField(
        @Mandatory Explorer explorer,
        @Mandatory Object object,
        int index,
        @Mandatory Class type,
        @Mandatory String label
    ) {
        super(explorer, object, index, checkType(type), label, UiStringFieldBase::new);
    }

    private static @Mandatory Class checkType(@Mandatory Class type){
        if(type == String.class){
            return type;
        } else {
            throw new IllegalStateException();
        }
    }
}
