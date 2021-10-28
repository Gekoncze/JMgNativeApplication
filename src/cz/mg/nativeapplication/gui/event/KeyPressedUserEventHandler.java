package cz.mg.nativeapplication.gui.event;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public @Utility class KeyPressedUserEventHandler extends EventHandler implements KeyListener {
    private final @Mandatory @Part Handler handler;

    public KeyPressedUserEventHandler(@Mandatory Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public void keyPressed(KeyEvent event) {
        run(() -> handler.run(event));
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    public interface Handler {
        void run(KeyEvent e);
    }
}
