package cz.mg.nativeapplication.gui.event;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public @Utility class FocusGainedUserEventHandler extends EventHandler implements FocusListener {
    private final @Mandatory @Part Handler handler;

    public FocusGainedUserEventHandler(@Mandatory Handler handler) {
        this.handler = handler;
    }

    @Override
    public void focusGained(FocusEvent focusEvent) {
        run(handler::run);
    }

    @Override
    public void focusLost(FocusEvent focusEvent) {
    }

    public interface Handler {
        void run();
    }
}
