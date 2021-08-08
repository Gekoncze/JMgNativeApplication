package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.handlers.FocusLostUserEventHandler;


public @Utility class UiIntegerField extends UiTextField {
    public UiIntegerField() {
        addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
    }

    public @Optional Integer getInteger(){
        try {
            return Integer.parseInt(getText().replace(" ", ""));
        } catch (Exception e){
            return null;
        }
    }

    public void setInteger(@Optional Integer value){
        if(value != null){
            setText(format(value));
        } else {
            setText("");
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
        setInteger(getInteger());
    }
}
