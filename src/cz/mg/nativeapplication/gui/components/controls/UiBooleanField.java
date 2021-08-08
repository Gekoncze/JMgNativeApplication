package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.handlers.FocusLostUserEventHandler;


public @Utility class UiBooleanField extends UiTextField {
    public UiBooleanField() {
        addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
    }

    public @Optional Boolean getBoolean(){
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

    public void setBoolean(@Optional Boolean value){
        if(value != null){
            setText(value.toString());
        } else {
            setText("");
        }
    }

    private void onFocusLost() {
        setBoolean(getBoolean());
    }
}
