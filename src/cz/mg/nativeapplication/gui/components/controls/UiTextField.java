package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public @Utility class UiTextField extends JTextField {
    private final @Mandatory @Shared Color greyColor;
    private @Optional @Shared String text;

    public UiTextField() {
        setBackground(UIManager.getDefaults().getColor("TextField.background"));
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

    @Override
    public void setText(@Optional String text) {
        super.setText(text != null ? text : "");
        this.text = text;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(text == null && !hasFocus()){
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
