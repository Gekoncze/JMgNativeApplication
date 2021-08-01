package cz.mg.nativeapplication.gui.handlers;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.components.dialogs.UiErrorMessageDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public @Utility class ActionUserEventHandler implements ActionListener {
    private final @Mandatory @Part Handler handler;

    public ActionUserEventHandler(@Mandatory Handler handler) {
        this.handler = handler;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            handler.run();
        } catch (Exception exception){
            new UiErrorMessageDialog(exception).show();
        }
    }

    public interface Handler {
        void run();
    }
}
