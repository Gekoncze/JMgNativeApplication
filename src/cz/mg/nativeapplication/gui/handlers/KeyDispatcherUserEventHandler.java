package cz.mg.nativeapplication.gui.handlers;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.components.dialogs.UiErrorMessageDialog;

import java.awt.*;
import java.awt.event.KeyEvent;


public class KeyDispatcherUserEventHandler implements KeyEventDispatcher {
    private final @Mandatory @Part Handler handler;

    public KeyDispatcherUserEventHandler(@Mandatory Handler handler) {
        this.handler = handler;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        try {
            handler.run(event);
        } catch (Exception exception){
            new UiErrorMessageDialog(exception).show();
        }
        return false;
    }

    public interface Handler {
        void run(KeyEvent e);
    }
}
