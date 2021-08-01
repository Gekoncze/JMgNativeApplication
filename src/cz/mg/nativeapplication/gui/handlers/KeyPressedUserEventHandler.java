package cz.mg.nativeapplication.gui.handlers;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.components.dialogs.UiErrorMessageDialog;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public @Utility class KeyPressedUserEventHandler implements KeyListener {
    private final @Mandatory @Part Handler handler;

    public KeyPressedUserEventHandler(@Mandatory Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public void keyPressed(KeyEvent event) {
        try {
            handler.run(event);
        } catch (Exception exception){
            new UiErrorMessageDialog(exception).show();
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    public interface Handler {
        void run(KeyEvent e);
    }
}
