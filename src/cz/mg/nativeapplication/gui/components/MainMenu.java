package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.gui.components.controls.UiMenu;
import cz.mg.nativeapplication.gui.components.controls.UiMenuBar;
import cz.mg.nativeapplication.gui.components.controls.UiMenuItem;

import static cz.mg.nativeapplication.gui.components.enums.Key.*;
import static cz.mg.nativeapplication.gui.components.enums.KeyModifier.CTRL;
import static cz.mg.nativeapplication.gui.components.enums.KeyModifier.SHIFT;


public @Utility class MainMenu extends UiMenuBar {
    public MainMenu(@Mandatory MainActions actions) {
        super(
            new UiMenu(
                "File", 'F',

                new UiMenuItem("New project", 'N', N, CTRL, actions::newProject),
                new UiMenuItem("Open project", 'O', O, CTRL, actions::openProject),
                new UiMenuItem("Save project", 'S', S, CTRL, actions::saveProject),
                new UiMenuItem("Save project as", 'A', S, CTRL | SHIFT, actions::saveProjectAs),
                new UiMenuItem("Close project", 'C', null, null, actions::closeProject),
                new UiMenuItem("Exit", 'E', null, null, actions::exit)
            ),

            new UiMenu(
                "Edit", 'E',

                new UiMenuItem("Undo", 'U', Z, CTRL, actions::undo),
                new UiMenuItem("Redo", 'E', Z, CTRL | SHIFT, actions::redo)
            ),

            new UiMenu(
                "View", 'V',

                new UiMenuItem("Refresh", null, F5, 0, actions::refresh),
                new UiMenuItem("Close active tab", null, W, CTRL, actions::closeActiveTab),
                new UiMenuItem("Next Tab", null, TAB, CTRL, actions::selectNextTab),
                new UiMenuItem("Previous Tab", null, TAB, CTRL | SHIFT, actions::selectPreviousTab)
            )
        );
    }
}
