package cz.mg.nativeapplication.gui.handlers;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.MainWindow;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public @Utility class FocusGainedUserEventHandler implements FocusListener {
    private final @Mandatory @Link MainWindow mainWindow;
    private final @Mandatory @Part Handler handler;

    public FocusGainedUserEventHandler(@Mandatory MainWindow mainWindow, @Mandatory Handler handler) {
        this.mainWindow = mainWindow;
        this.handler = handler;
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        try {
            handler.run();
        } catch (Exception e){
            mainWindow.showError(e);
        }
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
    }

    public interface Handler {
        void run();
    }
}
