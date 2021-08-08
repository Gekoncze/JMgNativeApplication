package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.gui.handlers.ActionUserEventHandler;

import javax.swing.*;


public @Utility class UiMenuItem extends JMenuItem implements UiComponent {
    public UiMenuItem(
        @Mandatory String name,
        @Optional Character mnemonic,
        @Optional Integer code,
        @Optional Integer modifiers,
        @Mandatory ActionUserEventHandler.Handler handler
    ) {
        super(name);
        if(mnemonic != null) setMnemonic(mnemonic);
        if(code != null && modifiers != null) setAccelerator(KeyStroke.getKeyStroke(code, modifiers));
        addActionListener(new ActionUserEventHandler(handler));
    }
}
