package cz.mg.nativeapplication.gui.handlers;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.MainWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public @Utility class KeyTypedUserEventHandler implements KeyListener {
    private final @Mandatory @Link MainWindow mainWindow;
    private final @Mandatory @Part Handler handler;

    public KeyTypedUserEventHandler(@Mandatory MainWindow mainWindow, @Mandatory Handler handler) {
        this.mainWindow = mainWindow;
        this.handler = handler;
    }

    @Override
    public void keyTyped(KeyEvent event) {
        try {
            handler.run(event);
        } catch (Exception e){
            mainWindow.showError(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    public interface Handler {
        void run(KeyEvent e);
    }
}
