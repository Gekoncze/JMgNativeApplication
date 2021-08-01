package cz.mg.nativeapplication.gui.handlers;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.components.dialogs.UiErrorMessageDialog;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public @Utility class CloseUserEventHandler implements WindowListener {
    private final @Mandatory @Part Handler handler;

    public CloseUserEventHandler(@Mandatory Handler handler) {
        this.handler = handler;
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {
    }

    @Override
    public void windowClosing(WindowEvent event) {
        try {
            handler.run();
        } catch (Exception exception){
            new UiErrorMessageDialog(exception).show();
        }
    }

    @Override
    public void windowClosed(WindowEvent event) {
    }

    @Override
    public void windowIconified(WindowEvent event) {
    }

    @Override
    public void windowDeiconified(WindowEvent event) {
    }

    @Override
    public void windowActivated(WindowEvent event) {
    }

    @Override
    public void windowDeactivated(WindowEvent event) {
    }

    public interface Handler {
        void run();
    }
}
