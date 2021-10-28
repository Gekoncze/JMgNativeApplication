package cz.mg.nativeapplication.gui.components.controls.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.event.FocusLostUserEventHandler;


public @Utility class UiBooleanField extends UiValueField {
    public UiBooleanField() {
        addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
    }

    @Override
    public @Optional Object getValue(){
        try {
            switch (getText().trim()){
                case "true": return true;
                case "false": return false;
                default: return null;
            }
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void setValue(@Optional Object value){
        if(value != null){
            if(value instanceof Boolean){
                setText(value.toString());
            } else {
                throw new IllegalArgumentException("Expected 'Boolean', but got '" + value.getClass().getSimpleName() + "'.");
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
