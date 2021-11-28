package cz.mg.nativeapplication.gui.ui.controls.field.other;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiFieldBase;


public @Utility interface UiFieldBaseFactory {
    @Mandatory UiFieldBase create();
}
