package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.explorer.services.UpdateService;
import cz.mg.nativeapplication.gui.ui.controls.UiButton;
import cz.mg.nativeapplication.gui.ui.controls.UiText;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiObjectFieldBase;
import cz.mg.nativeapplication.gui.ui.dialogs.UiConfirmDialog;
import cz.mg.nativeapplication.gui.components.entity.content.EntityMultiSelectContent;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySelectContent;
import cz.mg.nativeapplication.gui.components.entity.popups.EntityClassPopupMenu;
import cz.mg.nativeapplication.gui.event.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.images.ImageGallery;
import cz.mg.nativeapplication.gui.services.ApplicationProvider;

import java.awt.event.MouseEvent;


public @Utility class EntityPartSelect extends EntitySelect {
    private final @Mandatory @Shared ApplicationProvider applicationProvider = new ApplicationProvider();
    private final @Mandatory @Shared UpdateService updateService = new UpdateService();
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();

    private final @Mandatory @Shared UiText label;
    private final @Mandatory @Shared EntitySelectContent content;
    private final @Mandatory @Shared List<UiButton> buttons;
    private final @Mandatory @Shared EntityClassPopupMenu popupMenu;

    public EntityPartSelect(
        @Mandatory Object entity,
        @Mandatory EntityField entityField,
        @Mandatory EntitySelectType type
    ) {
        this.content = EntitySelectContent.create(entity, entityField, type, this::createContentField);
        this.label = new UiText(entityField.getName(), UiText.FontStyle.BOLD);
        this.buttons = new List<>(
            new UiButton(ImageGallery.CREATE, null, "Create", this::onCreateButtonClicked),
            new UiButton(ImageGallery.OPEN, null, "Open", this::onOpenButtonClicked),
            new UiButton(ImageGallery.DELETE, null, "Delete", this::onDeleteButtonClicked)
        );
        if(content instanceof EntityMultiSelectContent){
            this.buttons.addCollectionFirst(new List<>(
                new UiButton(ImageGallery.UP, null, "Move up", this::onMoveRowUp),
                new UiButton(ImageGallery.DOWN, null, "Move down", this::onMoveRowDown),
                new UiButton(ImageGallery.CREATE_ROW, null, "Add row", this::onAddRow),
                new UiButton(ImageGallery.DELETE_ROW, null, "Remove row", this::onRemoveRow)
            ));
        }
        this.popupMenu = new EntityClassPopupMenu(
            entityClassProvider.get(content.getType()),
            this::onCreateEntityClass
        );
        refresh();
    }

    @Override
    public @Mandatory UiText getLabel() {
        return label;
    }

    @Override
    public @Mandatory EntitySelectContent getContent() {
        return content;
    }

    @Override
    public @Mandatory List<UiButton> getButtons() {
        return buttons;
    }

    @Override
    public void refresh() {
        content.refresh();
    }

    private UiObjectFieldBase createContentField(){
        UiObjectFieldBase objectField = new UiObjectFieldBase();
        objectField.addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        return objectField;
    }

    private void onMouseClicked(MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                onOpenButtonClicked();
            }
        }
    }

    private void onCreateButtonClicked() {
        if(content.getFieldBase() != null){
            Object value = content.getValue();
            if(value == null){
                popupMenu.select(content.getFieldBase());
            }
        }
    }

    private void onOpenButtonClicked() {
        Object value = content.getValue();
        if(value != null){
            applicationProvider.get().getMainWindow().getMainView().getMainTabView().open(value);
        }
    }

    private boolean onDeleteButtonClicked() {
        if(content.getValue() != null){
            String title = "Delete entity?";
            String message = "Are you sure you want to delete the entity? Shared ownership might still keep it alive.";

            UiConfirmDialog.Choice choice = new UiConfirmDialog(title, message).show();
            if(choice == UiConfirmDialog.Choice.YES){
                updateService.update(
                    applicationProvider.get().getApplicationState().getProject(), // TODO - null check ???
                    content.getParent(),
                    content.getChildIndex(), // TODO - null check ???
                    null
                );
                applicationProvider.get().getMainWindow().refresh();
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private void onMoveRowUp() {
        ((EntityMultiSelectContent)content).moveRowUp();
    }

    private void onMoveRowDown() {
        ((EntityMultiSelectContent)content).moveRowDown();
    }

    private void onAddRow() {
        ((EntityMultiSelectContent)content).addRow();
    }

    private void onRemoveRow() {
        if(onDeleteButtonClicked()){
            ((EntityMultiSelectContent)content).removeRow();
        }
    }

    private void onCreateEntityClass(EntityClass entityClass){
        content.setValue(entityClass.newInstance());
        applicationProvider.get().getMainWindow().refresh();
    }
}
