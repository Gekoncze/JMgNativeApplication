package cz.mg.nativeapplication.gui.components.fields.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.explorer.Explorer;
import cz.mg.nativeapplication.gui.components.fields.ValueField;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiIntegerFieldBase;


public @Utility class IntegerValueField extends ValueField {
    public IntegerValueField(
        @Mandatory Explorer explorer,
        @Mandatory Object object,
        int index,
        @Mandatory Class type,
        @Mandatory String label
    ) {
        super(explorer, object, index, checkType(type), label, UiIntegerFieldBase::new);
    }

    private static @Mandatory Class checkType(@Mandatory Class type){
        if(type == Integer.class){
            return type;
        } else {
            throw new IllegalStateException();
        }
    }
}
