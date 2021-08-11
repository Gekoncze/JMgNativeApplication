package cz.mg.nativeapplication.gui.components.entity.single.part;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.*;
import cz.mg.nativeapplication.gui.components.dialogs.UiConfirmDialog;
import cz.mg.nativeapplication.gui.components.entity.single.EntitySingleSelect;
import cz.mg.nativeapplication.gui.handlers.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.sevices.entity.EntityClass;
import cz.mg.nativeapplication.sevices.entity.EntityClassMetadataProvider;
import cz.mg.nativeapplication.sevices.entity.EntityField;
import cz.mg.nativeapplication.sevices.gui.ObjectNameProvider;

import java.awt.event.MouseEvent;


public @Utility class EntityFieldPartSelect extends EntitySingleSelect {
    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared UiTextField content;
    private final @Mandatory @Shared List<UiButton> buttons;
    private final @Mandatory @Shared UiPopupMenu popupMenu;

    public EntityFieldPartSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        super(mainWindow, entity, entityField);
        this.label = new UiLabel(entityField.getName());
        this.content = new UiTextField();
        this.content.setEditable(false);
        this.content.addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        this.buttons = new List<>(
            new UiButton(mainWindow, IconGallery.CREATE, null, "Create", this::onCreateButtonClicked),
            new UiButton(mainWindow, IconGallery.EDIT, null, "Edit", this::onEditButtonClicked),
            new UiButton(mainWindow, IconGallery.DELETE, null, "Delete", this::onDeleteButtonClicked)
        );
        this.popupMenu = new UiPopupMenu();
        refresh();
    }

    @Override
    public @Mandatory UiLabel getLabel() {
        return label;
    }

    @Override
    public @Mandatory UiTextField getContent() {
        return content;
    }

    @Override
    public @Mandatory List<UiButton> getButtons() {
        return buttons;
    }

    @Override
    public void refresh() {
        Object object = getValue();
        content.setText(new ObjectNameProvider().get(object));
        content.setNull(object == null);
    }

    private void onMouseClicked(MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                onEditButtonClicked();
            }
        }
    }

    private void onDeleteButtonClicked() {
        if(getValue() != null){
            String title = "Delete entity?";
            String message = "Are you sure you want to delete the entity? All of its parts will be deleted too.";

            UiConfirmDialog.Choice choice = new UiConfirmDialog(title, message).show();
            if(choice == UiConfirmDialog.Choice.YES){
                // todo: delete this entity and all its references
                // todo: + delete all of its parts and all their references
            }
            refresh();
        }
    }

    private void onCreateButtonClicked() {
        Object value = getValue();
        if(value == null){
            EntityClass entityClass = new EntityClassMetadataProvider().get(entityField.getType());
            if(entityClass.getSubclasses().count() == 1){
                setValue(entityClass.newInstance());
            } else if(entityClass.getSubclasses().count() > 1) {
                popupMenu.removeAll();
                for(EntityClass option : entityClass.getSubclasses()){
                    popupMenu.add(new UiMenuItem(option.getName(), () -> setValue(option.newInstance())));
                }
                popupMenu.show(content, 0, content.getHeight());
            }
        }
    }

    private void onEditButtonClicked() {
        Object child = entityField.get(entity);
        if(child != null){
            mainWindow.getMainView().getMainTabView().open(child);
        }
    }
}
