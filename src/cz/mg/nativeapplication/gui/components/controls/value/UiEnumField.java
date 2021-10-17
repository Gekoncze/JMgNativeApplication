package cz.mg.nativeapplication.gui.components.controls.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.gui.handlers.FocusLostUserEventHandler;


public @Utility class UiEnumField<T extends Enum> extends UiValueField {
    private final @Mandatory @Link Class<T> enumClass;

    public UiEnumField(@Mandatory Class<T> enumClass) {
        this.enumClass = enumClass;
        addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
    }

    @Override
    public @Optional Object getValue(){
        String text = getText();
        for(T value : enumClass.getEnumConstants()){
            if(value.name().equals(text)){
                return value;
            }
        }
        return null;
    }

    public void setValue(@Optional Object value){
        if(value != null){
            if(enumClass.isInstance(value)){
                setText(((T)value).name());
            } else {
                throw new IllegalArgumentException("Expected '" + enumClass.getSimpleName() + "', but got '" + value.getClass().getSimpleName() + "'.");
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
