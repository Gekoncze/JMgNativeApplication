package cz.mg.nativeapplication.gui.handlers;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.MainWindow;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public @Utility class ChangeUserEventHandler implements ChangeListener {
    private final @Mandatory @Link MainWindow mainWindow;
    private final @Mandatory @Part Handler handler;

    public ChangeUserEventHandler(@Mandatory MainWindow mainWindow, @Mandatory Handler handler) {
        this.mainWindow = mainWindow;
        this.handler = handler;
    }

    public void stateChanged(){
        stateChanged(new ChangeEvent(new Object()));
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        try {
            handler.run();
        } catch (Exception e){
            mainWindow.showError(e);
        }
    }

    public interface Handler {
        void run();
    }
}
