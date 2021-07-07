package cz.mg.nativeapplication.gui.handlers;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.MainWindow;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class CloseUserEventHandler implements WindowListener {
    private final @Mandatory @Link MainWindow mainWindow;
    private final @Mandatory @Part Handler handler;

    public CloseUserEventHandler(@Mandatory MainWindow mainWindow, @Mandatory Handler handler) {
        this.mainWindow = mainWindow;
        this.handler = handler;
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        try {
            handler.run();
        } catch (Exception e){
            mainWindow.showError(e);
        }
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {
    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {
    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {
    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {
    }

    public interface Handler {
        void run();
    }
}
