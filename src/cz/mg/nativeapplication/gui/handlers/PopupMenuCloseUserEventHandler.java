package cz.mg.nativeapplication.gui.handlers;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.MainWindow;

import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;


public @Utility class PopupMenuCloseUserEventHandler implements PopupMenuListener {
    private final @Mandatory @Link MainWindow mainWindow;
    private final @Mandatory @Part Handler handler;

    public PopupMenuCloseUserEventHandler(@Mandatory MainWindow mainWindow, @Mandatory Handler handler) {
        this.mainWindow = mainWindow;
        this.handler = handler;
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent popupMenuEvent) {
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent popupMenuEvent) {
        try {
            handler.run();
        } catch (Exception e){
            mainWindow.showError(e);
        }
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent popupMenuEvent) {
    }

    public interface Handler {
        void run();
    }
}
