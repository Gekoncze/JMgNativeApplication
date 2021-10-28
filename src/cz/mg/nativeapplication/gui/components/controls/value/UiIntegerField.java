package cz.mg.nativeapplication.gui.components.controls.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.event.FocusLostUserEventHandler;


public @Utility class UiIntegerField extends UiValueField {
    public UiIntegerField() {
        addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
    }

    @Override
    public @Optional Object getValue(){
        try {
            return Integer.parseInt(getText().trim().replace(" ", ""));
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void setValue(@Optional Object value){
        if(value != null){
            if(value instanceof Integer){
                setText(format((Integer)value));
            } else {
                throw new IllegalArgumentException("Expected 'Integer', but got '" + value.getClass().getSimpleName() + "'.");
            }
        } else {
            setText("");
            setNull(true);
        }
    }

    private @Mandatory String format(@Mandatory Integer value){
        String text = value.toString();
        StringBuilder formattedText = new StringBuilder();
        int ii = 0;
        for(int i = text.length() - 1; i >= 0; i--){
            char ch = text.charAt(i);
            formattedText.insert(0, ch);
            if((ii+1) % 3 == 0) formattedText.insert(0, ' ');
            ii++;
        }
        return formattedText.toString().replace("- ", "-").trim();
    }

    private void onFocusLost() {
        this.setValue(getValue());
    }
}
