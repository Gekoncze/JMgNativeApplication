package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiPopupMenu;
import cz.mg.nativeapplication.gui.components.controls.UiText;
import cz.mg.nativeapplication.gui.components.controls.value.UiValueField;
import cz.mg.nativeapplication.gui.components.entity.content.EntityMultiSelectContent;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySelectContent;
import cz.mg.nativeapplication.gui.components.enums.Key;
import cz.mg.nativeapplication.gui.event.FocusLostUserEventHandler;
import cz.mg.nativeapplication.gui.event.KeyPressedUserEventHandler;
import cz.mg.nativeapplication.gui.event.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.icons.ImageGallery;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public abstract @Utility class EntityValueSelect extends EntitySelect {
    private final @Mandatory @Shared UiText label;
    private final @Mandatory @Shared EntitySelectContent content;
    private final @Mandatory @Shared List<UiButton> buttons;
    private final @Optional @Shared UiPopupMenu popupMenu;

    public EntityValueSelect(
        @Mandatory Object entity,
        @Mandatory EntityField entityField,
        @Mandatory EntitySelectType type
    ) {
        this.content = EntitySelectContent.create(entity, entityField, type, this::createContentField);
        this.label = new UiText(entityField.getName(), UiText.FontStyle.BOLD);
        this.buttons = new List<>(
            new UiButton(ImageGallery.EDIT, null, "Edit", this::onEditButtonClicked),
            new UiButton(ImageGallery.CLEAR, null, "Clear", this::onClearButtonClicked)
        );
        if(content instanceof EntityMultiSelectContent){
            this.buttons.addCollectionFirst(new List<>(
                    new UiButton(ImageGallery.UP, null, "Move up", this::onMoveRowUp),
                    new UiButton(ImageGallery.DOWN, null, "Move down", this::onMoveRowDown),
                    new UiButton(ImageGallery.CREATE_ROW, null, "Add row", this::onAddRow),
                    new UiButton(ImageGallery.DELETE_ROW, null, "Remove row", this::onRemoveRow)
            ));
        }
        this.popupMenu = createPopupMenu(content);
        refresh();
    }

    @Override
    public @Mandatory UiText getLabel() {
        return label;
    }

    @Override
    public @Mandatory EntitySelectContent getContent(){
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

    private UiValueField createContentField(){
        UiValueField valueField = createValueField(content);
        valueField.addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        valueField.addKeyListener(new KeyPressedUserEventHandler(this::onKeyPressed));
        valueField.addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
        return valueField;
    }

    protected abstract @Mandatory UiValueField createValueField(@Mandatory EntitySelectContent content);
    protected abstract @Optional UiPopupMenu createPopupMenu(@Mandatory EntitySelectContent content);

    private void showSelectionMenu(){
        if(content.getField() != null){
            if(popupMenu != null){
                popupMenu.show(content.getField());
            }
        }
    }

    private void onEditButtonClicked() {
        if(content.getField() != null){
            content.getField().unlock();
            showSelectionMenu();
        }
    }

    private void onClearButtonClicked(){
        content.setValue(null);
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
        ((EntityMultiSelectContent)content).removeRow();
    }

    private void onMouseClicked(MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                onEditButtonClicked();
            }
        }
    }

    private void onKeyPressed(KeyEvent event) {
        if(event.getKeyCode() == Key.ESCAPE){
            refresh();
            event.consume();
        }

        if(event.getKeyCode() == Key.ENTER){
            if(content.getField() != null){
                content.setValue(content.getField().getValue());
                event.consume();
            }
        }
    }

    private void onFocusLost() {
        if(popupMenu == null || !popupMenu.isVisible()){
            refresh();
        }
    }
}
