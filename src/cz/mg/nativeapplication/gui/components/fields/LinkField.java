package cz.mg.nativeapplication.gui.components.fields;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySelectContent;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySingleSelectContent;
import cz.mg.nativeapplication.gui.components.popups.ComponentSearchPopupMenu;
import cz.mg.nativeapplication.gui.components.enums.Key;
import cz.mg.nativeapplication.gui.event.FocusGainedUserEventHandler;
import cz.mg.nativeapplication.gui.event.FocusLostUserEventHandler;
import cz.mg.nativeapplication.gui.event.KeyPressedUserEventHandler;
import cz.mg.nativeapplication.gui.event.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.images.ImageGallery;
import cz.mg.nativeapplication.gui.services.MainWindowProvider;
import cz.mg.nativeapplication.gui.ui.controls.UiButton;
import cz.mg.nativeapplication.gui.ui.controls.UiText;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiObjectFieldBase;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public @Utility class LinkField extends ObjectField {
    private final @Mandatory @Shared MainWindowProvider mainWindowProvider = new MainWindowProvider();

    private final @Mandatory @Shared UiText label;
    private final @Mandatory @Shared EntitySelectContent content;
    private final @Mandatory @Shared UiButton searchButton;
    private final @Mandatory @Shared UiButton openButton;
    private final @Mandatory @Shared UiButton editButton;
    private final @Mandatory @Shared UiButton clearButton;
    private final @Mandatory @Shared ComponentSearchPopupMenu popupMenu;

    public LinkField(@Mandatory Object entity, @Mandatory EntityField entityField) {
        this.content = new EntitySingleSelectContent(entity, entityField, this::createContentField);
        this.label = new UiText(content.getName(), UiText.FontStyle.BOLD);
        this.searchButton = new UiButton(ImageGallery.SEARCH, null, "Search", this::onSearchButtonClicked);
        this.openButton = new UiButton(ImageGallery.OPEN, null, "Open", this::onOpenButtonClicked);
        this.editButton = new UiButton(ImageGallery.EDIT, null, "Edit", this::onEditButtonClicked);
        this.clearButton = new UiButton(ImageGallery.CLEAR, null, "Clear", this::onClearButtonClicked);
        this.popupMenu = new ComponentSearchPopupMenu(content::setValue);
        addHorizontal(label, 0, 0, Alignment.MIDDLE, Fill.BOTH);
        addHorizontal(content.getField(), 0, 0, Alignment.MIDDLE, Fill.BOTH);
        addHorizontal(searchButton, 0, 0, Alignment.MIDDLE, Fill.BOTH);
        addHorizontal(openButton, 0, 0, Alignment.MIDDLE, Fill.BOTH);
        addHorizontal(editButton, 0, 0, Alignment.MIDDLE, Fill.BOTH);
        addHorizontal(clearButton, 0, 0, Alignment.MIDDLE, Fill.BOTH);
        rebuild();
        refresh();
    }

    @Override
    public void refresh() {
        content.refresh();
    }

    private UiObjectFieldBase createContentField(){
        UiObjectFieldBase objectField = new UiObjectFieldBase();
        objectField.addFocusListener(new FocusGainedUserEventHandler(this::onFocusGained));
        objectField.addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
        objectField.addKeyListener(new KeyPressedUserEventHandler(this::onKeyPressed));
        objectField.addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        return objectField;
    }

    private void onFocusGained(){
        if(content.getFieldBase() != null){
            content.getFieldBase().getFieldBase().selectAll();
        }
    }

    private void onFocusLost(){
        if(!popupMenu.isVisible()){
            content.softRefresh();
        }
    }

    private void onKeyPressed(@Mandatory KeyEvent event) {
        if(event.getKeyCode() == Key.ESCAPE){
            refresh();
        }

        if(event.getKeyCode() == Key.SPACE && event.isControlDown()){
            showSelectionMenu();
            event.consume();
        }
    }

    private void onMouseClicked(@Mandatory MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                if(content.getFieldBase() != null){
                    content.getFieldBase().unlock();
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
        if(content.getFieldBase() != null){
            content.getFieldBase().unlock();
        }
    }

    private void onClearButtonClicked(){
        content.setValue(null);
    }

    private void showSelectionMenu(){
        if(content.getFieldBase() != null){
            popupMenu.search(
                content.getFieldBase(),
                content.getType(),
                content.getFieldBase().getFieldBase().getText(),
                mainWindowProvider.get().getNavigation()
            );
        }
    }
}
