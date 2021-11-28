package cz.mg.nativeapplication.gui.ui.controls.menu;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.ui.controls.UiComponent;
import cz.mg.nativeapplication.gui.event.ActionUserEventHandler;

import javax.swing.*;
import java.awt.*;


public @Utility class UiMenuItem extends JMenuItem implements UiComponent {
    public UiMenuItem(
        @Optional Image image,
        @Mandatory String name,
        @Optional Character mnemonic,
        @Optional Integer code,
        @Optional Integer modifiers,
        @Mandatory ActionUserEventHandler.Handler handler
    ) {
        super(name);
        if(image != null) setIcon(new ImageIcon(image));
        if(mnemonic != null) setMnemonic(mnemonic);
        if(code != null && modifiers != null) setAccelerator(KeyStroke.getKeyStroke(code, modifiers));
        addActionListener(new ActionUserEventHandler(handler));
    }

    public UiMenuItem(
        @Optional Image image,
        @Mandatory String name,
        @Mandatory ActionUserEventHandler.Handler handler
    ){
        this(image, name, null, null, null, handler);
    }
}
