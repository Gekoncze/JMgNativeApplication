package cz.mg.nativeapplication.gui.handlers;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.components.dialogs.UiErrorMessageDialog;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public @Utility class FocusLostUserEventHandler implements FocusListener {
    private final @Mandatory @Part Handler handler;

    public FocusLostUserEventHandler(@Mandatory Handler handler) {
        this.handler = handler;
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        try {
            handler.run();
        } catch (Exception exception){
            new UiErrorMessageDialog(exception).show();
        }
    }

    public interface Handler {
        void run();
    }
}
