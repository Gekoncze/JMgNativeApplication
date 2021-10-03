package cz.mg.nativeapplication.gui.components.entity.single.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.*;
import cz.mg.nativeapplication.gui.components.entity.single.EntitySingleSelect;
import cz.mg.nativeapplication.gui.components.enums.Key;
import cz.mg.nativeapplication.gui.handlers.FocusLostUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.KeyPressedUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public @Utility class EntityBooleanValueSingleSelect extends EntitySingleSelect {
    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared UiBooleanField content;
    private final @Mandatory @Shared List<UiButton> buttons;
    private final @Mandatory @Shared UiPopupMenu popupMenu;

    public EntityBooleanValueSingleSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        super(mainWindow, entity, entityField);
        this.label = new UiLabel(entityField.getName());
        this.content = new UiBooleanField();
        this.content.addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        this.content.addKeyListener(new KeyPressedUserEventHandler(this::onKeyPressed));
        this.content.addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
        this.buttons = new List<>(
            new UiButton(mainWindow, IconGallery.EDIT, null, "Edit", this::onEditButtonClicked),
            new UiButton(mainWindow, IconGallery.CLEAR, null, "Clear", this::onClearButtonClicked)
        );
        this.popupMenu = new UiPopupMenu(
            new UiMenuItem(null, "true", () -> setValue(true)),
            new UiMenuItem(null, "false", () -> setValue(false))
        );
        refresh();
    }

    @Override
    public final @Mandatory UiLabel getLabel() {
        return label;
    }

    @Override
    public @Mandatory UiTextField getContent(){
        return content;
    }

    @Override
    public final @Mandatory List<UiButton> getButtons() {
        return buttons;
    }

    @Override
    public void refresh() {
        Object value = getValue();
        content.setBoolean((Boolean)value);
        content.setNull(value == null);
        content.lock();
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
            setValue(content.getBoolean());
            refresh();
            event.consume();
        }
    }

    private void onFocusLost() {
        if(!popupMenu.isVisible()){
            refresh();
        }
    }

    private void onClearButtonClicked() {
        setValue(null);
    }

    private void onEditButtonClicked() {
        content.unlock();
        showSelectionMenu();
    }
    
    private void showSelectionMenu(){
        popupMenu.show(content, 0, content.getHeight());
    }
}