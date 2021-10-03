package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public @Utility class UiTextField extends JTextField implements UiComponent {
    private static final @Mandatory @Shared Color GREY_COLOR = new Color(160, 160, 160);
    private @Value boolean isNull = false;
    private @Value boolean locked = false;

    public UiTextField() {
        setBackground(copy(UIManager.getDefaults().getColor("TextField.background")));
        setBorder(BorderFactory.createEtchedBorder());
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
        lock();
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

    public void lock(){
        locked = true;
        setEditable(false);
        getCaret().setVisible(false);
    }

    public void unlock(){
        locked = false;
        setEditable(true);
        requestFocus();
        getCaret().setVisible(true);
    }

    public boolean isLocked() {
        return locked;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isNull && getText().length() <= 0){
            if(!hasFocus() || !isEditable()){
                g.setColor(GREY_COLOR);
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
