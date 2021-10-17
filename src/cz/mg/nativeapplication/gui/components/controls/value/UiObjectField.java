package cz.mg.nativeapplication.gui.components.controls.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.gui.services.ObjectNameProvider;


public @Utility class UiObjectField extends UiValueField {
    private final @Mandatory @Shared ObjectNameProvider objectNameProvider = new ObjectNameProvider();

    @Override
    public @Optional Object getValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue(@Optional Object value) {
        if(value != null){
            setText(objectNameProvider.get(value));
        } else {
            setText("");
            setNull(true);
        }
    }
}
