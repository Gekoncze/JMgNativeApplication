package cz.mg.nativeapplication.gui.components.entity.single.link;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.popups.ComponentSearchPopupMenu;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.*;
import cz.mg.nativeapplication.gui.components.entity.single.EntitySingleSelect;
import cz.mg.nativeapplication.gui.components.enums.Key;
import cz.mg.nativeapplication.gui.handlers.*;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.services.ObjectNameProvider;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public @Utility class EntityLinkSingleSelect extends EntitySingleSelect {
    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared UiTextField content;
    private final @Mandatory @Shared List<UiButton> buttons;
    private final @Mandatory @Shared ComponentSearchPopupMenu popupMenu;

    public EntityLinkSingleSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        super(mainWindow, entity, entityField);
        this.label = new UiLabel(entityField.getName());
        this.content = new UiTextField();
        this.content.addFocusListener(new FocusGainedUserEventHandler(this::onFocusGained));
        this.content.addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
        this.content.addKeyListener(new KeyPressedUserEventHandler(this::onKeyPressed));
        this.content.addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        this.buttons = new List<>(
            new UiButton(mainWindow, IconGallery.SEARCH, null, "Search", this::onSearchButtonClicked),
            new UiButton(mainWindow, IconGallery.EDIT, null, "Edit", this::onEditButtonClicked),
            new UiButton(mainWindow, IconGallery.CLEAR, null, "Clear", this::onClearButtonClicked)
        );
        this.popupMenu = new ComponentSearchPopupMenu(this::onItemSelected);
        lock();
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
    public List<UiButton> getButtons() {
        return buttons;
    }

    @Override
    public void refresh() {
        Object value = getValue();
        content.setText(new ObjectNameProvider().get(value));
        content.setNull(value == null);
    }

    private void onFocusGained(){
        content.selectAll();
    }

    private void onFocusLost(){
        if(!popupMenu.isVisible()){
            lock();
        }
    }

    private void onKeyPressed(KeyEvent event) {
        if(event.getKeyCode() == Key.ESCAPE){
            lock();
        }

        if(event.getKeyCode() == Key.SPACE && event.isControlDown()){
            showSelectionMenu();
            event.consume();
        }
    }

    private void onMouseClicked(MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                unlock();
            }
        }
    }

    private void onClearButtonClicked(){
        setValue(null);
    }

    private void onSearchButtonClicked(){
        showSelectionMenu();
    }

    private void onEditButtonClicked(){
        unlock();
    }

    private void showSelectionMenu(){
        popupMenu.search(
            content,
            entityField.getField().getType(),
            content.getText(),
            mainWindow.getNavigation()
        );
    }

    private void onItemSelected(){
        if(popupMenu.getSelectedComponent() != null){
            setValue(popupMenu.getSelectedComponent());
        }
    }

    private void lock(){
        content.setEditable(false);
        content.getCaret().setVisible(false);
        refresh();
    }

    private void unlock(){
        content.setEditable(true);
        content.requestFocus();
        content.getCaret().setVisible(true);
    }
}
