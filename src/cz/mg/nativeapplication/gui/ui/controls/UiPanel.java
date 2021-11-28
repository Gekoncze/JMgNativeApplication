package cz.mg.nativeapplication.gui.ui.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;

import javax.swing.*;
import java.awt.*;

import static cz.mg.nativeapplication.gui.ui.controls.UiPanel.Alignment.MIDDLE;
import static cz.mg.nativeapplication.gui.ui.controls.UiPanel.Fill.BOTH;


public @Utility class UiPanel extends JPanel implements UiComponent {
    private final @Value int border;
    private final @Value int padding;
    private final @Mandatory @Value HorizontalAlignment horizontalAlignment;
    private final @Mandatory @Value VerticalAlignment verticalAlignment;
    protected final @Mandatory @Part List<ComponentSettings> components = new List<>();

    public UiPanel(int border, int padding, @Mandatory Alignment alignment) {
        this.border = border;
        this.padding = padding;
        this.horizontalAlignment = alignment.getHorizontal();
        this.verticalAlignment = alignment.getVertical();
        setOpaque(false);
        setBackground(null);
        rebuild();
    }

    public void add(
        @Mandatory Component component,
        int x, int y, int wx, int wy,
        @Mandatory Alignment alignment,
        @Mandatory Fill fill
    ){
        add(component, x, y, wx, wy, alignment, fill, 1, 1);
    }

    public void add(
        @Mandatory Component component,
        int x, int y, int wx, int wy,
        @Mandatory Alignment alignment,
        @Mandatory Fill fill,
        int spanX, int spanY
    ){
        components.addLast(new ComponentSettings(component, x, y, wx, wy, alignment, fill, spanX, spanY));
    }

    public void clear(){
        removeAll();
        components.clear();
    }

    public void rebuild(){
        removeAll();
        setLayout(new GridBagLayout());

        int maxX = 0;
        int maxY = 0;
        int maxWX = 0;
        int maxWY = 0;

        for(ComponentSettings component : components){
            maxX = Math.max(maxX, component.x);
            maxY = Math.max(maxY, component.y);
            maxWX = Math.max(maxWX, component.wx);
            maxWY = Math.max(maxWY, component.wy);
        }

        for(ComponentSettings component : components){
            int pLeft = component.x == 0 ? border : 0;
            int pRight = component.x == maxX ? border : padding;
            int pTop = component.y == 0 ? border : 0;
            int pBottom = component.y == maxY ? border : padding;

            add(component.component, constraints(
                component.x, component.y, component.wx, component.wy,
                pTop, pLeft, pBottom, pRight,
                component.alignment, component.fill,
                component.spanX, component.spanY
            ));
        }

        if(maxWX == 0){
            if(horizontalAlignment == HorizontalAlignment.LEFT){
                add(createDummy(), constraints(-1, 0, 1, 0, 0, 0, 0, 0, MIDDLE, BOTH, 1, 1));
            }

            if(horizontalAlignment == HorizontalAlignment.RIGHT){
                add(createDummy(), constraints(maxX + 1, 0, 1, 0, 0, 0, 0, 0, MIDDLE, BOTH, 1, 1));
            }
        }

        if(maxWY == 0){
            if(verticalAlignment == VerticalAlignment.TOP){
                add(createDummy(), constraints(0, -1, 0, 1, 0, 0, 0, 0, MIDDLE, BOTH, 1, 1));
            }

            if(verticalAlignment == VerticalAlignment.BOTTOM){
                add(createDummy(), constraints(0, maxY + 1, 0, 1, 0, 0, 0, 0, MIDDLE, BOTH, 1, 1));
            }
        }

        repaint();
        revalidate();
    }

    private JPanel createDummy(){
        JPanel dummy = new JPanel();
        dummy.setMinimumSize(new Dimension(0, 0));
        dummy.setPreferredSize(new Dimension(0, 0));
        dummy.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        dummy.setOpaque(false);
        dummy.setBackground(null);
        return dummy;
    }

    private GridBagConstraints constraints(
        int x, int y, int wx, int wy,
        int pTop, int pLeft, int pBottom, int pRight,
        @Mandatory Alignment alignment,
        @Mandatory Fill fill,
        int spanX, int spanY
    ){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.weightx = wx;
        constraints.weighty = wy;
        constraints.fill = fill.getFill();
        constraints.insets = new Insets(pTop, pLeft, pBottom, pRight);
        constraints.anchor = alignment.getAnchor();
        constraints.gridwidth = spanX;
        constraints.gridheight = spanY;
        return constraints;
    }

    private static class ComponentSettings {
        public final @Mandatory @Shared Component component;
        public final @Value int x;
        public final @Value int y;
        public final @Value int wx;
        public final @Value int wy;
        public final @Mandatory @Value Alignment alignment;
        public final @Mandatory @Value Fill fill;
        public final @Value int spanX;
        public final @Value int spanY;

        public ComponentSettings(
            @Mandatory Component component,
            int x, int y, int wx, int wy,
            @Mandatory Alignment alignment,
            @Mandatory Fill fill,
            int spanX, int spanY
        ) {
            this.component = component;
            this.x = x;
            this.y = y;
            this.wx = wx;
            this.wy = wy;
            this.alignment = alignment;
            this.fill = fill;
            this.spanX = spanX;
            this.spanY = spanY;
        }
    }

    public enum HorizontalAlignment {
        LEFT, MIDDLE, RIGHT
    }

    public enum VerticalAlignment {
        TOP, MIDDLE, BOTTOM
    }

    public enum Alignment {
        TOP_LEFT, TOP, TOP_RIGHT,
        LEFT, MIDDLE, RIGHT,
        BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT;

        public HorizontalAlignment getHorizontal(){
            switch (this){
                case TOP_LEFT: return HorizontalAlignment.LEFT;
                case LEFT: return HorizontalAlignment.LEFT;
                case BOTTOM_LEFT: return HorizontalAlignment.LEFT;

                case TOP: return HorizontalAlignment.MIDDLE;
                case MIDDLE: return HorizontalAlignment.MIDDLE;
                case BOTTOM: return HorizontalAlignment.MIDDLE;

                case TOP_RIGHT: return HorizontalAlignment.RIGHT;
                case RIGHT: return HorizontalAlignment.RIGHT;
                case BOTTOM_RIGHT: return HorizontalAlignment.RIGHT;
            }
            throw new IllegalStateException();
        }

        public VerticalAlignment getVertical(){
            switch (this){
                case TOP_LEFT: return VerticalAlignment.TOP;
                case TOP: return VerticalAlignment.TOP;
                case TOP_RIGHT: return VerticalAlignment.TOP;

                case LEFT: return VerticalAlignment.MIDDLE;
                case MIDDLE: return VerticalAlignment.MIDDLE;
                case RIGHT: return VerticalAlignment.MIDDLE;

                case BOTTOM_LEFT: return VerticalAlignment.BOTTOM;
                case BOTTOM: return VerticalAlignment.BOTTOM;
                case BOTTOM_RIGHT: return VerticalAlignment.BOTTOM;
            }
            throw new IllegalStateException();
        }

        int getAnchor(){
            switch (this){
                case TOP_LEFT: return GridBagConstraints.FIRST_LINE_START;
                case TOP: return GridBagConstraints.PAGE_START;
                case TOP_RIGHT: return GridBagConstraints.FIRST_LINE_END;

                case LEFT: return GridBagConstraints.LINE_START;
                case MIDDLE: return GridBagConstraints.CENTER;
                case RIGHT: return GridBagConstraints.LINE_END;

                case BOTTOM_LEFT: return GridBagConstraints.LAST_LINE_START;
                case BOTTOM: return GridBagConstraints.PAGE_END;
                case BOTTOM_RIGHT: return GridBagConstraints.LAST_LINE_END;
            }
            throw new IllegalStateException();
        }
    }

    public enum Fill {
        NONE,
        HORIZONTAL,
        VERTICAL,
        BOTH;

        int getFill(){
            switch (this){
                case NONE: return GridBagConstraints.NONE;
                case HORIZONTAL: return GridBagConstraints.HORIZONTAL;
                case VERTICAL: return GridBagConstraints.VERTICAL;
                case BOTH: return GridBagConstraints.BOTH;
            }
            throw new IllegalStateException();
        }
    }
}
