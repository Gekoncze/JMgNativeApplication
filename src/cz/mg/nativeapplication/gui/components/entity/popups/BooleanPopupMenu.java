package cz.mg.nativeapplication.gui.components.entity.popups;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.gui.ui.controls.UiPopupMenu;
import cz.mg.nativeapplication.gui.ui.controls.menu.UiValueMenuItem;

import static cz.mg.nativeapplication.gui.ui.controls.menu.UiValueMenuItem.SelectEventHandler;


public @Utility class BooleanPopupMenu extends UiPopupMenu {
    public BooleanPopupMenu(@Mandatory SelectEventHandler<Boolean> handler) {
        super(
            new UiValueMenuItem<>(true, "true", handler),
            new UiValueMenuItem<>(false, "false", handler)
        );
    }
}
