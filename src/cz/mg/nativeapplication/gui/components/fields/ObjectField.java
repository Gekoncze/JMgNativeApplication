package cz.mg.nativeapplication.gui.components.fields;

import cz.mg.annotations.classes.Utility;
import cz.mg.nativeapplication.gui.components.other.Refreshable;
import cz.mg.nativeapplication.gui.ui.controls.UiPanel;


public abstract @Utility class ObjectField extends UiPanel implements Refreshable {
    private static final int BORDER = 0;
    private static final int PADDING = 0;

    protected ObjectField() {
        super(BORDER, PADDING, Alignment.MIDDLE);
    }
}
