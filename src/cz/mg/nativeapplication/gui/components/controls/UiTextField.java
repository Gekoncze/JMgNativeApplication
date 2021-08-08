package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public @Utility class UiTextField extends JTextField {
    private final @Mandatory @Shared Color greyColor;
    private @Value boolean isNull = false;

    public UiTextField() {
        setBackground(copy(UIManager.getDefaults().getColor("TextField.background")));
        setBorder(BorderFactory.createEtchedBorder());
        greyColor = new Color(160, 160, 160);
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                repaint();
            }
        });
    }

    private static @Mandatory Color copy(@Mandatory Color color){
        return new Color(
            color.getRed(),
            color.getGreen(),
            color.getBlue(),
            color.getAlpha()
        );
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean value) {
        isNull = value;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isNull && getText().length() <= 0){
            if(!hasFocus() || !isEditable()){
                g.setColor(greyColor);
                g.setFont(getFont());
                g.drawString(
                    "null",
                    getInsets().left,
                    getHeight() / 2 + g.getFontMetrics().getAscent() / 2 - 2
                );
            }
        }
    }
}
