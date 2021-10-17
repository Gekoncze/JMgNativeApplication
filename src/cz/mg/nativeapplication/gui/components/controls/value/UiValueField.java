package cz.mg.nativeapplication.gui.components.controls.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.components.controls.UiTextField;


public abstract @Utility class UiValueField extends UiTextField {
    public UiValueField() {
    }

    public abstract @Optional Object getValue();
    public abstract void setValue(@Optional Object value);
}
