package cz.mg.nativeapplication.gui.ui;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.ui.controls.UiPanel;

import java.awt.*;

import static cz.mg.nativeapplication.gui.ui.controls.UiPanel.Alignment.LEFT;
import static cz.mg.nativeapplication.gui.ui.controls.UiPanel.Fill.BOTH;


public @Utility class UiHorizontalPanelBuilder {
    private int border = 0;
    private int padding = 0;
    private int wx = 0;
    private int wy = 0;
    private @Mandatory UiPanel.Alignment contentAlignment = LEFT;
    private @Mandatory UiPanel.Alignment componentAlignment = LEFT;
    private @Mandatory UiPanel.Fill fill = BOTH;
    private final @Mandatory @Shared List<Component> components = new List();

    public UiHorizontalPanelBuilder() {
    }

    public @Mandatory UiHorizontalPanelBuilder setBorder(int border){
        this.border = border;
        return this;
    }

    public @Mandatory UiHorizontalPanelBuilder setPadding(int padding) {
        this.padding = padding;
        return this;
    }

    public @Mandatory UiHorizontalPanelBuilder setWeightX(int wx) {
        this.wx = wx;
        return this;
    }

    public @Mandatory UiHorizontalPanelBuilder setWeightY(int wy) {
        this.wy = wy;
        return this;
    }

    public @Mandatory UiHorizontalPanelBuilder setContentAlignment(@Mandatory UiPanel.Alignment contentAlignment) {
        this.contentAlignment = contentAlignment;
        return this;
    }

    public @Mandatory UiHorizontalPanelBuilder setComponentAlignment(@Mandatory UiPanel.Alignment componentAlignment) {
        this.componentAlignment = componentAlignment;
        return this;
    }

    public @Mandatory UiHorizontalPanelBuilder setFill(@Mandatory UiPanel.Fill fill) {
        this.fill = fill;
        return this;
    }

    public @Mandatory UiHorizontalPanelBuilder addComponent(@Mandatory Component component){
        components.addLast(component);
        return this;
    }

    public @Mandatory UiHorizontalPanelBuilder addComponents(@Mandatory Iterable<? extends Component> components){
        this.components.addCollectionLast(components);
        return this;
    }

    public UiPanel build(){
        UiPanel panel = new UiPanel(border, padding, contentAlignment);
        for(Component component : components){
            panel.addHorizontal(component, wx, wy, componentAlignment, fill);
        }
        panel.rebuild();
        return panel;
    }
}