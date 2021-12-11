package cz.mg.nativeapplication.gui.components.fields;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.explorer.Explorer;
import cz.mg.nativeapplication.gui.event.FocusLostUserEventHandler;
import cz.mg.nativeapplication.gui.event.KeyPressedUserEventHandler;
import cz.mg.nativeapplication.gui.event.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.images.ImageGallery;
import cz.mg.nativeapplication.gui.services.ObjectImageProvider;
import cz.mg.nativeapplication.gui.ui.controls.UiButton;
import cz.mg.nativeapplication.gui.ui.controls.UiText;
import cz.mg.nativeapplication.gui.ui.controls.field.UiValueField;
import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldBaseFactory;
import cz.mg.nativeapplication.gui.ui.enums.UiFill;
import cz.mg.nativeapplication.gui.ui.enums.UiKey;
import cz.mg.nativeapplication.gui.ui.enums.alignment.UiAlignment;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public abstract @Utility class ValueField extends ObjectField {
    private final @Mandatory @Shared ObjectImageProvider objectImageProvider = new ObjectImageProvider();

    private final @Mandatory @Shared UiText label;
    protected final @Mandatory @Shared UiValueField field;
    private final @Mandatory @Shared UiButton editButton;
    private final @Mandatory @Shared UiButton clearButton;

    protected ValueField(
        @Mandatory Explorer explorer,
        @Mandatory Object object,
        int index,
        @Mandatory Class type,
        @Mandatory String label,
        @Mandatory UiFieldBaseFactory fieldBaseFactory
    ) {
        super(explorer, object, index, type);
        this.label = new UiText(label, UiText.FontStyle.BOLD);
        this.field = new UiValueField(objectImageProvider::getOptional, fieldBaseFactory);
        this.field.getBase().addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        this.field.getBase().addKeyListener(new KeyPressedUserEventHandler(this::onKeyPressed));
        this.field.getBase().addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
        this.editButton = new UiButton(ImageGallery.EDIT, null, "Edit", this::onEditButtonClicked);
        this.clearButton = new UiButton(ImageGallery.CLEAR, null, "Clear", this::onClearButtonClicked);
        addHorizontal(this.label, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.field, 1, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.editButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.clearButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        rebuild();
        refresh();
    }

    protected void onEditButtonClicked() {
        field.unlock();
    }

    private void onClearButtonClicked(){
        setValue(null);
    }

    private void onMouseClicked(MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                onEditButtonClicked();
            }
        }
    }

    private void onKeyPressed(KeyEvent event) {
        if(event.getKeyCode() == UiKey.ESCAPE){
            refresh();
            event.consume();
        }

        if(event.getKeyCode() == UiKey.ENTER){
            setValue(field.getBase().getValue());
            event.consume();
        }
    }

    protected void onFocusLost() {
        refresh();
    }

    @Override
    public void refresh() {
        field.setValue(getValue());
        field.lock();
    }
}
