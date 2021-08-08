package cz.mg.nativeapplication.gui.components.entity.single.part;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.entity.EntitySelect;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.controls.UiPanel;
import cz.mg.nativeapplication.gui.components.controls.UiTextField;
import cz.mg.nativeapplication.gui.components.entity.single.EntitySingleSelect;
import cz.mg.nativeapplication.gui.handlers.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.history.SetEntityFieldAction;
import cz.mg.nativeapplication.sevices.EntityClass;
import cz.mg.nativeapplication.sevices.EntityClassCache;
import cz.mg.nativeapplication.sevices.EntityField;
import cz.mg.nativeapplication.sevices.gui.ObjectNameProvider;

import javax.swing.*;

import java.awt.event.MouseEvent;

import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.LEFT;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.MIDDLE;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.BOTH;
import static cz.mg.nativeapplication.gui.other.NavigationCache.Node;


public @Utility class EntityFieldPartSelect implements EntitySingleSelect {
    private static final int PADDING = 2;

    private final @Mandatory @Link MainWindow mainWindow;
    private final @Mandatory @Link Object entity;
    private final @Mandatory @Link EntityField entityField;

    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared UiTextField textField;
    private final @Mandatory @Shared UiPanel buttons;
    private final @Mandatory @Shared UiButton deleteButton;
    private final @Mandatory @Shared UiButton createButton;
    private final @Mandatory @Shared UiButton editButton;

    public EntityFieldPartSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        this.mainWindow = mainWindow;
        this.entity = entity;
        this.entityField = entityField;
        this.label = new UiLabel(entityField.getName());
        this.textField = new UiTextField();
        this.textField.setEditable(false);
        this.textField.addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        this.deleteButton = new UiButton(mainWindow, IconGallery.DELETE, null, "Delete", this::onDeleteButtonClicked);
        this.createButton = new UiButton(mainWindow, IconGallery.CREATE, null, "Create", this::onCreateButtonClicked);
        this.editButton = new UiButton(mainWindow, IconGallery.EDIT, null, "Edit", this::onEditButtonClicked);
        this.buttons = new UiPanel(0, PADDING, LEFT);
        this.buttons.add(deleteButton, 0, 0, 0, 0, MIDDLE, BOTH);
        this.buttons.add(createButton, 1, 0, 0, 0, MIDDLE, BOTH);
        this.buttons.add(editButton, 2, 0, 0, 0, MIDDLE, BOTH);
        this.buttons.rebuild();
        refresh();
    }

    @Override
    public @Mandatory UiLabel getLabel() {
        return label;
    }

    @Override
    public @Mandatory UiTextField getContent() {
        return textField;
    }

    @Override
    public @Mandatory UiPanel getButtons() {
        return buttons;
    }

    @Override
    public void refresh() {
        Object object = entityField.get(entity);
        textField.setText(new ObjectNameProvider().getDisplayName(object));
        textField.setNull(object == null);
    }

    private void onMouseClicked(MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                onEditButtonClicked();
            }
        }
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
            mainWindow.getApplicationState().getHistory().run(
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