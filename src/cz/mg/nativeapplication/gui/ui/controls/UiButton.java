package cz.mg.nativeapplication.gui.ui.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.gui.event.ActionUserEventHandler;
import cz.mg.nativeapplication.gui.services.ApplicationProvider;

import javax.swing.*;
import java.awt.*;


public @Utility class UiButton extends JButton implements UiComponent {
    private final @Mandatory @Shared ApplicationProvider applicationProvider = new ApplicationProvider();

    public UiButton(
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
            setIcon(new ImageIcon(applicationProvider.get().getImageGallery().getImage(iconName)));
        }

        if(text != null){
            setText(text);
        }

        if(tooltip != null){
            setToolTipText(tooltip);
        }
    }
}
