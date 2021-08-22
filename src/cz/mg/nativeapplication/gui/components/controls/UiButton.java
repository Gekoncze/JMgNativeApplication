package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.handlers.ActionUserEventHandler;
import cz.mg.nativeapplication.gui.services.IconGalleryProvider;

import javax.swing.*;
import java.awt.*;


public @Utility class UiButton extends JButton implements UiComponent {
    public UiButton(
        @Mandatory MainWindow mainWindow,
        @Optional String iconName,
        @Optional String text,
        @Optional String tooltip,
        @Mandatory ActionUserEventHandler.Handler handler
    ) {
        addActionListener(new ActionUserEventHandler(handler));
        setBackground(new Color(0, 0, 0, 0));
        setBorder(null);
        setOpaque(false);

        if(iconName != null){
            setIcon(new IconGalleryProvider().get().getIcon(iconName));
        }

        if(text != null){
            setText(text);
        }

        if(tooltip != null){
            setToolTipText(tooltip);
        }
    }
}
