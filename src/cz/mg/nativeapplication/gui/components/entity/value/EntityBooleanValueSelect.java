package cz.mg.nativeapplication.gui.components.entity.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.controls.value.UiBooleanField;
import cz.mg.nativeapplication.gui.components.controls.value.UiValueField;
import cz.mg.nativeapplication.gui.components.entity.EntitySelect;
import cz.mg.nativeapplication.gui.components.entity.EntitySelectType;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySelectContent;
import cz.mg.nativeapplication.gui.components.entity.popups.BooleanPopupMenu;
import cz.mg.nativeapplication.gui.components.enums.Key;
import cz.mg.nativeapplication.gui.handlers.FocusLostUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.KeyPressedUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public @Utility class EntityBooleanValueSelect extends EntitySelect {
    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared EntitySelectContent content;
    private final @Mandatory @Shared List<UiButton> buttons;
    private final @Mandatory @Shared BooleanPopupMenu popupMenu;

    public EntityBooleanValueSelect(
        @Mandatory Object entity,
        @Mandatory EntityField entityField,
        @Mandatory EntitySelectType type
    ) {
        this.content = EntitySelectContent.create(entity, entityField, type, this::createContentField);
        this.label = new UiLabel(entityField.getName());
        this.buttons = new List<>(
            new UiButton(IconGallery.EDIT, null, "Edit", this::onEditButtonClicked),
            new UiButton(IconGallery.CLEAR, null, "Clear", this::onClearButtonClicked)
        );
        this.buttons.addCollectionFirst(content.getButtons());
        this.popupMenu = new BooleanPopupMenu(content::setValue);
        refresh();
    }

    @Override
    public @Mandatory UiLabel getLabel() {
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
        UiBooleanField valueField = new UiBooleanField();
        valueField.addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        valueField.addKeyListener(new KeyPressedUserEventHandler(this::onKeyPressed));
        valueField.addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
        return valueField;
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
        if(!popupMenu.isVisible()){
            refresh();
        }
    }

    private void onClearButtonClicked() {
        content.setValue(null);
    }

    private void onEditButtonClicked() {
        if(content.getField() != null){
            content.getField().unlock();
            showSelectionMenu();
        }
    }

    private void showSelectionMenu(){
        if(content.getField() != null){
            popupMenu.show(content.getField());
        }
    }
}
