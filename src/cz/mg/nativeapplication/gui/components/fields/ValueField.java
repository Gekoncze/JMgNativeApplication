package cz.mg.nativeapplication.gui.components.fields;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySelectContent;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySingleSelectContent;
import cz.mg.nativeapplication.gui.components.enums.Key;
import cz.mg.nativeapplication.gui.event.FocusLostUserEventHandler;
import cz.mg.nativeapplication.gui.event.KeyPressedUserEventHandler;
import cz.mg.nativeapplication.gui.event.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.images.ImageGallery;
import cz.mg.nativeapplication.gui.ui.controls.UiButton;
import cz.mg.nativeapplication.gui.ui.controls.UiPopupMenu;
import cz.mg.nativeapplication.gui.ui.controls.UiText;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiFieldBase;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public abstract @Utility class ValueField extends ObjectField {
    private final @Mandatory @Shared UiText label;
    private final @Mandatory @Shared EntitySelectContent content;
    private final @Mandatory @Shared UiButton editButton;
    private final @Mandatory @Shared UiButton clearButton;
    private final @Optional @Shared UiPopupMenu popupMenu;

    public ValueField(@Mandatory Object entity, @Mandatory EntityField entityField) {
        this.content = new EntitySingleSelectContent(entity, entityField, this::createContentField);
        this.label = new UiText(entityField.getName(), UiText.FontStyle.BOLD);
        this.editButton = new UiButton(ImageGallery.EDIT, null, "Edit", this::onEditButtonClicked);
        this.clearButton = new UiButton(ImageGallery.CLEAR, null, "Clear", this::onClearButtonClicked);
        this.popupMenu = createPopupMenu(content);
        addHorizontal(label, 0, 0, Alignment.MIDDLE, Fill.BOTH);
        addHorizontal(content.getField(), 1, 0, Alignment.MIDDLE, Fill.BOTH);
        addHorizontal(editButton, 0, 0, Alignment.MIDDLE, Fill.BOTH);
        addHorizontal(clearButton, 0, 0, Alignment.MIDDLE, Fill.BOTH);
        rebuild();
        refresh();
    }

    @Override
    public void refresh() {
        content.refresh();
    }

    private UiFieldBase createContentField(){
        UiFieldBase valueField = createValueField(content);
        valueField.addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        valueField.addKeyListener(new KeyPressedUserEventHandler(this::onKeyPressed));
        valueField.addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
        return valueField;
    }

    protected abstract @Mandatory UiFieldBase createValueField(@Mandatory EntitySelectContent content);
    protected abstract @Optional UiPopupMenu createPopupMenu(@Mandatory EntitySelectContent content);

    private void showSelectionMenu(){
        if(content.getFieldBase() != null){
            if(popupMenu != null){
                popupMenu.show(content.getFieldBase());
            }
        }
    }

    private void onEditButtonClicked() {
        if(content.getFieldBase() != null){
            content.getFieldBase().unlock();
            showSelectionMenu();
        }
    }

    private void onClearButtonClicked(){
        content.setValue(null);
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
            if(content.getFieldBase() != null){
                content.setValue(content.getFieldBase().getValue());
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
