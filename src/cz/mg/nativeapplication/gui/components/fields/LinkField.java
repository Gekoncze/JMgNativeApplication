package cz.mg.nativeapplication.gui.components.fields;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.Explorer;
import cz.mg.nativeapplication.gui.ui.enums.alignment.UiAlignment;
import cz.mg.nativeapplication.gui.ui.enums.UiFill;
import cz.mg.nativeapplication.gui.ui.enums.UiKey;
import cz.mg.nativeapplication.gui.components.popups.ComponentSearchPopupMenu;
import cz.mg.nativeapplication.gui.event.FocusGainedUserEventHandler;
import cz.mg.nativeapplication.gui.event.FocusLostUserEventHandler;
import cz.mg.nativeapplication.gui.event.KeyPressedUserEventHandler;
import cz.mg.nativeapplication.gui.event.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.images.ImageGallery;
import cz.mg.nativeapplication.gui.services.MainWindowProvider;
import cz.mg.nativeapplication.gui.services.ObjectImageProvider;
import cz.mg.nativeapplication.gui.ui.controls.UiButton;
import cz.mg.nativeapplication.gui.ui.controls.UiText;
import cz.mg.nativeapplication.gui.ui.controls.field.UiValueField;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiObjectFieldBase;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public @Utility class LinkField extends ObjectField {
    private final @Mandatory @Shared MainWindowProvider mainWindowProvider = new MainWindowProvider();
    private final @Mandatory @Shared ObjectImageProvider objectImageProvider = new ObjectImageProvider();

    private final @Mandatory @Shared UiText label;
    private final @Mandatory @Shared UiValueField field;
    private final @Mandatory @Shared UiButton searchButton;
    private final @Mandatory @Shared UiButton openButton;
    private final @Mandatory @Shared UiButton editButton;
    private final @Mandatory @Shared UiButton clearButton;
    private final @Mandatory @Shared ComponentSearchPopupMenu popupMenu;

    public LinkField(
        @Mandatory Explorer explorer,
        @Mandatory Object object,
        int index,
        @Mandatory Class type,
        @Mandatory String label
    ) {
        super(explorer, object, index, type);
        this.label = new UiText(label, UiText.FontStyle.BOLD);
        this.field = new UiValueField(objectImageProvider::getOptional, UiObjectFieldBase::new);
        this.field.getBase().addFocusListener(new FocusGainedUserEventHandler(this::onFocusGained));
        this.field.getBase().addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
        this.field.getBase().addKeyListener(new KeyPressedUserEventHandler(this::onKeyPressed));
        this.field.getBase().addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        this.searchButton = new UiButton(ImageGallery.SEARCH, null, "Search", this::onSearchButtonClicked);
        this.openButton = new UiButton(ImageGallery.OPEN, null, "Open", this::onOpenButtonClicked);
        this.editButton = new UiButton(ImageGallery.EDIT, null, "Edit", this::onEditButtonClicked);
        this.clearButton = new UiButton(ImageGallery.CLEAR, null, "Clear", this::onClearButtonClicked);
        this.popupMenu = new ComponentSearchPopupMenu(this::setValue);
        addHorizontal(this.label, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.field, 1, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.searchButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.openButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.editButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.clearButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        rebuild();
        refresh();
    }

    private void onFocusGained(){
        field.getBase().selectAll();
    }

    private void onFocusLost(){
        if(!popupMenu.isVisible()){
            refresh();
        }
    }

    private void onKeyPressed(@Mandatory KeyEvent event) {
        if(event.getKeyCode() == UiKey.ESCAPE){
            refresh();
        }

        if(event.getKeyCode() == UiKey.SPACE && event.isControlDown()){
            showSelectionMenu();
            event.consume();
        }
    }

    private void onMouseClicked(@Mandatory MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                field.unlock();
            }
        }
    }

    private void onSearchButtonClicked(){
        showSelectionMenu();
    }

    private void onOpenButtonClicked(){
        mainWindowProvider
            .get()
            .getMainView()
            .getMainTabView()
            .open(getValue());
    }

    private void onEditButtonClicked(){
        field.unlock();
    }

    private void onClearButtonClicked(){
        setValue(null);
    }

    private void showSelectionMenu(){
        popupMenu.search(
            field.getBase(),
            getType(),
            field.getBase().getText(),
            mainWindowProvider.get().getNavigation()
        );
    }

    @Override
    public void refresh() {
        field.setValue(getValue());
        field.lock();
    }
}
