package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.ui.controls.menu.UiMenu;
import cz.mg.nativeapplication.gui.ui.controls.menu.UiMenuBar;
import cz.mg.nativeapplication.gui.ui.controls.menu.UiMenuItem;

import static cz.mg.nativeapplication.gui.components.enums.Key.*;
import static cz.mg.nativeapplication.gui.components.enums.KeyModifier.CTRL;
import static cz.mg.nativeapplication.gui.components.enums.KeyModifier.SHIFT;


public @Utility class MainMenu extends UiMenuBar {
    private final @Mandatory @Part MainActions actions;

    public MainMenu() {
        actions = new MainActions();

        add(new UiMenu(
            "File", 'F',

            new UiMenuItem(null, "New project", 'N', N, CTRL, actions::newProject),
            new UiMenuItem(null, "Open project", 'O', O, CTRL, actions::openProject),
            new UiMenuItem(null, "Save project", 'S', S, CTRL, actions::saveProject),
            new UiMenuItem(null, "Save project as", 'A', S, CTRL | SHIFT, actions::saveProjectAs),
            new UiMenuItem(null, "Close project", 'C', null, null, actions::closeProject),
            new UiMenuItem(null, "Exit", 'E', null, null, actions::exit)
        ));

        add(new UiMenu(
            "Edit", 'E',

            new UiMenuItem(null, "Undo", 'U', Z, CTRL, actions::undo),
            new UiMenuItem(null, "Redo", 'E', Z, CTRL | SHIFT, actions::redo)
        ));

        add(new UiMenu(
            "View", 'V',

            new UiMenuItem(null, "Refresh", null, F5, 0, actions::refresh),
            new UiMenuItem(null, "Close active tab", null, W, CTRL, actions::closeActiveTab),
            new UiMenuItem(null, "Next Tab", null, TAB, CTRL, actions::selectNextTab),
            new UiMenuItem(null, "Previous Tab", null, TAB, CTRL | SHIFT, actions::selectPreviousTab)
        ));
    }

    public @Mandatory MainActions getActions() {
        return actions;
    }
}
