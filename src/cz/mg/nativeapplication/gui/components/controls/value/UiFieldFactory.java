package cz.mg.nativeapplication.gui.components.controls.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;


public @Utility interface UiFieldFactory {
    @Mandatory UiValueField create();
}
