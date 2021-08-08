package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.gui.handlers.FocusLostUserEventHandler;


public @Utility class UiEnumField<T extends Enum> extends UiTextField {
    private final @Mandatory @Link Class<T> enumClass;

    public UiEnumField(@Mandatory Class<T> enumClass) {
        this.enumClass = enumClass;
        addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
    }

    public @Optional T getEnum(){
        String text = getText();
        for(T value : enumClass.getEnumConstants()){
            if(value.name().equals(text)){
                return value;
            }
        }
        return null;
    }

    public void setEnum(@Optional T value){
        if(value != null){
            setText(value.name());
        } else {
            setText("");
        }
    }

    private void onFocusLost() {
        setEnum(getEnum());
    }
}
