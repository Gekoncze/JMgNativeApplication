package cz.mg.nativeapplication.gui.handlers;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.components.dialogs.UiErrorMessageDialog;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public @Utility class MouseClickUserEventHandler implements MouseListener {
    private final @Mandatory @Part Handler handler;

    public MouseClickUserEventHandler(@Mandatory Handler handler) {
        this.handler = handler;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        try {
            handler.run(event);
        } catch (Exception exception){
            new UiErrorMessageDialog(exception).show();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    public interface Handler {
        void run(MouseEvent e);
    }
}
