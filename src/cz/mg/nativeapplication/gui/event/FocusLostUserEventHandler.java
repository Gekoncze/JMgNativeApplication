package cz.mg.nativeapplication.gui.event;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public @Utility class FocusLostUserEventHandler extends EventHandler implements FocusListener {
    private final @Mandatory @Part Handler handler;

    public FocusLostUserEventHandler(@Mandatory Handler handler) {
        this.handler = handler;
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
        run(handler::run);
    }

    public interface Handler {
        void run();
    }
}
