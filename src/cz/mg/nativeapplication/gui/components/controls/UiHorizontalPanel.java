package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.requirement.Mandatory;

import java.awt.*;


public class UiHorizontalPanel extends UiPanel {
    public UiHorizontalPanel(int border, int padding, @Mandatory Alignment alignment) {
        super(border, padding, alignment);
    }

    public void add(@Mandatory Component component, int wx, int wy, @Mandatory Alignment alignment, @Mandatory Fill fill) {
        add(component, getComponentCount(), 0, wx, wy, alignment, fill);
    }
}
