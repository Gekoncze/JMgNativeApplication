package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.requirement.Mandatory;

import java.awt.*;


public class UiVerticalPanel extends UiPanel implements UiComponent {
    public UiVerticalPanel(int border, int padding, @Mandatory Alignment alignment) {
        super(border, padding, alignment);
    }

    public void add(@Mandatory Component component, int wx, int wy, @Mandatory Alignment alignment, @Mandatory Fill fill) {
        add(component, 0, components.count(), wx, wy, alignment, fill);
    }
}
