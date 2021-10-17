package cz.mg.nativeapplication.gui.components.controls.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.handlers.FocusLostUserEventHandler;


public @Utility class UiStringField extends UiValueField {
    public UiStringField() {
        addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
    }

    @Override
    public @Optional Object getValue() {
        if(isNull()){
            return null;
        } else {
            return getText();
        }
    }

    @Override
    public void setValue(@Optional Object value) {
        if(value != null){
            if(value instanceof String){
                setText((String)value);
            } else {
                throw new IllegalArgumentException("Expected 'String', but got '" + value.getClass().getSimpleName() + "'.");
            }
        } else {
            setText("");
            setNull(true);
        }
    }

    private void onFocusLost() {
        setValue(getValue());
    }
}
