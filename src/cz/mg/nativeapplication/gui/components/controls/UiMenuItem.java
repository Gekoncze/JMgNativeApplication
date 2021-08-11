package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.handlers.ActionUserEventHandler;

import javax.swing.*;


public @Utility class UiMenuItem extends JMenuItem implements UiComponent {
    public UiMenuItem(
        @Optional Icon icon,
        @Mandatory String name,
        @Optional Character mnemonic,
        @Optional Integer code,
        @Optional Integer modifiers,
        @Mandatory ActionUserEventHandler.Handler handler
    ) {
        super(name);
        if(icon != null) setIcon(icon);
        if(mnemonic != null) setMnemonic(mnemonic);
        if(code != null && modifiers != null) setAccelerator(KeyStroke.getKeyStroke(code, modifiers));
        addActionListener(new ActionUserEventHandler(handler));
    }

    public UiMenuItem(
        @Optional Icon icon,
        @Mandatory String name,
        @Mandatory ActionUserEventHandler.Handler handler
    ){
        this(icon, name, null, null, null, handler);
    }
}
