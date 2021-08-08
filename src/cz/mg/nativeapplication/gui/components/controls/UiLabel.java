package cz.mg.nativeapplication.gui.components.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseListener;

import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.LEFT;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.MIDDLE;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.BOTH;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.NONE;


public @Utility class UiLabel extends UiPanel implements UiComponent {
    private static final int PADDING = 4;

    private final @Optional @Shared JLabel label;
    private final @Optional @Shared JTextField textField;

    public UiLabel(@Optional String text) {
        this(null, text);
    }

    public UiLabel(@Optional Icon icon) {
        this(icon, null);
    }

    public UiLabel(@Optional Icon icon, @Optional String text) {
        super(0, PADDING, LEFT);

        if(icon != null){
            label = new JLabel();
            label.setIcon(icon);
            add(label, 0, 0, 0, 0, MIDDLE, BOTH);
        } else {
            label = null;
        }

        if(text != null){
            textField = new JTextField(text);
            textField.setEditable(false);
            textField.setBorder(null);
            textField.setBackground(null);
            textField.setOpaque(false);
            textField.setFont(new Font(
                textField.getFont().getName(),
                Font.BOLD,
                textField.getFont().getSize()
            ));
            textField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    textField.setSelectionStart(0);
                    textField.setSelectionEnd(0);
                }
            });
            add(textField, 1, 0, 0, 0, MIDDLE, NONE);
        } else {
            textField = null;
        }

        rebuild();
    }

    @Override
    public synchronized void addMouseListener(MouseListener listener) {
        super.addMouseListener(listener);
        if(label != null) label.addMouseListener(listener);
        if(textField != null) textField.addMouseListener(listener);
    }
}
