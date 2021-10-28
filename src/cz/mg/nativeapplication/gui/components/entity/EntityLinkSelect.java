package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.controls.value.UiObjectField;
import cz.mg.nativeapplication.gui.components.entity.content.EntityMultiSelectContent;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySelectContent;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.enums.Key;
import cz.mg.nativeapplication.gui.components.entity.popups.ComponentSearchPopupMenu;
import cz.mg.nativeapplication.gui.handlers.FocusGainedUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.FocusLostUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.KeyPressedUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.services.MainWindowProvider;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public @Utility class EntityLinkSelect extends EntitySelect {
    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared EntitySelectContent content;
    private final @Mandatory @Shared List<UiButton> buttons;
    private final @Mandatory @Shared ComponentSearchPopupMenu popupMenu;

    private final @Mandatory @Shared MainWindowProvider mainWindowProvider = new MainWindowProvider();

    public EntityLinkSelect(
        @Mandatory Object entity,
        @Mandatory EntityField entityField,
        @Mandatory EntitySelectType type
    ) {
        this.content = EntitySelectContent.create(entity, entityField, type, this::createContentField);
        this.label = new UiLabel(content.getName());
        this.buttons = new List<>(
            new UiButton(IconGallery.SEARCH, null, "Search", this::onSearchButtonClicked),
            new UiButton(IconGallery.OPEN, null, "Open", this::onOpenButtonClicked),
            new UiButton(IconGallery.EDIT, null, "Edit", this::onEditButtonClicked),
            new UiButton(IconGallery.CLEAR, null, "Clear", this::onClearButtonClicked)
        );
        if(content instanceof EntityMultiSelectContent){
            this.buttons.addCollectionFirst(new List<>(
                new UiButton(IconGallery.UP, null, "Move up", this::onMoveRowUp),
                new UiButton(IconGallery.DOWN, null, "Move down", this::onMoveRowDown),
                new UiButton(IconGallery.CREATE_ROW, null, "Add row", this::onAddRow),
                new UiButton(IconGallery.DELETE_ROW, null, "Remove row", this::onRemoveRow)
            ));
        }
        this.popupMenu = new ComponentSearchPopupMenu(content::setValue);
        refresh();
    }

    @Override
    public @Mandatory UiLabel getLabel() {
        return label;
    }

    @Override
    public @Mandatory EntitySelectContent getContent() {
        return content;
    }

    @Override
    public List<UiButton> getButtons() {
        return buttons;
    }

    @Override
    public void refresh() {
        content.refresh();
    }

    private UiObjectField createContentField(){
        UiObjectField objectField = new UiObjectField();
        objectField.addFocusListener(new FocusGainedUserEventHandler(this::onFocusGained));
        objectField.addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
        objectField.addKeyListener(new KeyPressedUserEventHandler(this::onKeyPressed));
        objectField.addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        return objectField;
    }

    private void onFocusGained(){
        if(content.getField() != null){
            content.getField().selectAll();
        }
    }

    private void onFocusLost(){
        if(!popupMenu.isVisible()){
            refresh();
        }
    }

    private void onKeyPressed(KeyEvent event) {
        if(event.getKeyCode() == Key.ESCAPE){
            refresh();
        }

        if(event.getKeyCode() == Key.SPACE && event.isControlDown()){
            showSelectionMenu();
            event.consume();
        }
    }

    private void onMouseClicked(MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                if(content.getField() != null){
                    content.getField().unlock();
                }
            }
        }
    }

    private void onSearchButtonClicked(){
        showSelectionMenu();
    }

    private void onOpenButtonClicked(){
        Object value = content.getValue();
        if(value != null){
            mainWindowProvider.get().getMainView().getMainTabView().open(value);
        }
    }

    private void onEditButtonClicked(){
        if(content.getField() != null){
            content.getField().unlock();
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

    private void showSelectionMenu(){
        if(content.getField() != null){
            popupMenu.search(
                content.getField(),
                content.getType(),
                content.getField().getText(),
                mainWindowProvider.get().getNavigation()
            );
        }
    }
}
