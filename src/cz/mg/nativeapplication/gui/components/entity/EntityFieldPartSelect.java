package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.components.RefreshableView;
import cz.mg.nativeapplication.gui.handlers.ActionUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.utilities.GridSettingsFactory;
import cz.mg.nativeapplication.history.SetEntityFieldAction;
import cz.mg.nativeapplication.sevices.EntityClass;
import cz.mg.nativeapplication.sevices.EntityClassCache;
import cz.mg.nativeapplication.sevices.EntityField;
import cz.mg.nativeapplication.sevices.gui.ObjectNameProvider;

import javax.swing.*;
import java.awt.*;

import static cz.mg.nativeapplication.gui.utilities.NavigationCache.Node;


public @Utility class EntityFieldPartSelect implements RefreshableView {
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
        this.textField.setBackground(UIManager.getDefaults().getColor("TextField.background"));
        this.textField.setBorder(BorderFactory.createEtchedBorder());
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
        if(entityField.get(entity) != null){
            String title = "Delete entity?";
            String message = "Are you sure you want to delete the entity? All of its parts will be deleted too.";
            int option = JOptionPane.showConfirmDialog(mainWindow, message, title, JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                // todo: delete this entity and all its references
                // todo: + delete all of its parts and all their references
            }
            refresh();
        }
    }

    private void onCreateButtonClicked() {
        Object child = entityField.get(entity);
        if(child == null){
            // todo: if there are subclasses, add a choice for what to create
            EntityClass entityClass = EntityClassCache.getInstance().get(entityField.getType());
            mainWindow.getHistory().run(
                new SetEntityFieldAction(
                    entityField, entity, entityClass.newInstance(), null
                )
            );
            refresh();
        }
    }

    private void onEditButtonClicked() {
        Object child = entityField.get(entity);
        if(child != null){
            Node node = mainWindow.getNavigationCache().get(child);
            if(node != null){
                mainWindow.getMainView().getMainTabView().open(node);
            } else {
                // todo: handle other types of non-component entities
            }
        }
    }
}
