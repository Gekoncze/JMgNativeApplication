package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.components.RefreshableComponent;
import cz.mg.nativeapplication.gui.handlers.ActionUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.utilities.GridSettingsFactory;
import cz.mg.nativeapplication.sevices.EntityField;
import cz.mg.nativeapplication.sevices.gui.ObjectNameProvider;

import javax.swing.*;
import java.awt.*;


public @Utility class EntityFieldPartSelect implements RefreshableComponent {
    private static final int PADDING = 2;

    private final @Mandatory MainWindow mainWindow;
    private final @Mandatory @Link JLabel label;
    private final @Mandatory @Link JTextField textField;
    private final @Mandatory @Link JPanel buttons;
    private final @Mandatory @Link JButton deleteButton;
    private final @Mandatory @Link JButton createButton;
    private final @Mandatory @Link JButton editButton;
    private final @Mandatory Object entity;
    private final @Mandatory EntityField entityField;

    public EntityFieldPartSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        this.mainWindow = mainWindow;
        this.label = new JLabel(entityField.getName());
        this.textField = new JTextField();
        this.textField.setEditable(false);
        this.buttons = new JPanel();
        this.buttons.setLayout(new GridBagLayout());
        this.deleteButton = new JButton(mainWindow.getIconGallery().getIcon(IconGallery.DELETE));
        this.deleteButton.addActionListener(new ActionUserEventHandler(mainWindow, this::onDeleteButtonClicked));
        this.deleteButton.setToolTipText("Delete");
        this.deleteButton.setBackground(new Color(0, 0, 0, 0));
        this.deleteButton.setBorder(null);
        this.deleteButton.setOpaque(false);
        this.createButton = new JButton(mainWindow.getIconGallery().getIcon(IconGallery.CREATE));
        this.createButton.addActionListener(new ActionUserEventHandler(mainWindow, this::onCreateButtonClicked));
        this.createButton.setToolTipText("Create");
        this.createButton.setBackground(new Color(0, 0, 0, 0));
        this.createButton.setBorder(null);
        this.createButton.setOpaque(false);
        this.editButton = new JButton(mainWindow.getIconGallery().getIcon(IconGallery.EDIT));
        this.editButton.addActionListener(new ActionUserEventHandler(mainWindow, this::onEditButtonClicked));
        this.editButton.setToolTipText("Edit");
        this.editButton.setBackground(new Color(0, 0, 0, 0));
        this.editButton.setBorder(null);
        this.editButton.setOpaque(false);
        this.buttons.add(deleteButton, new GridSettingsFactory().create(0, 0, 0, 0, 0, 0, 0, PADDING));
        this.buttons.add(createButton, new GridSettingsFactory().create(1, 0, 0, 0, 0, PADDING, 0, PADDING));
        this.buttons.add(editButton, new GridSettingsFactory().create(2, 0, 0, 0, 0, PADDING, 0, 0));
        this.buttons.add(new JPanel(), new GridSettingsFactory().create(3, 0, 1, 0, 0)); // dummy alignment panel
        this.entity = entity;
        this.entityField = entityField;
        refresh();
    }

    public @Mandatory JLabel getLabel() {
        return label;
    }

    public @Mandatory JTextField getTextField() {
        return textField;
    }

    public @Mandatory JPanel getButtons() {
        return buttons;
    }

    @Override
    public void refresh() {
        textField.setText(new ObjectNameProvider().getDisplayName(entityField.get(entity)));
    }

    private void onDeleteButtonClicked() {
        // todo;
    }

    private void onCreateButtonClicked() {
        // todo;
    }

    private void onEditButtonClicked() {
        // todo;
    }
}
