package cz.mg.nativeapplication.gui.components.entity.single.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiIntegerField;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.controls.UiTextField;
import cz.mg.nativeapplication.gui.components.entity.single.EntitySingleSelect;
import cz.mg.nativeapplication.gui.components.enums.Key;
import cz.mg.nativeapplication.gui.handlers.FocusLostUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.KeyPressedUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.history.SetEntityFieldAction;
import cz.mg.nativeapplication.sevices.EntityField;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public @Utility class EntityIntegerFieldValueSelect implements EntitySingleSelect {
    private final @Mandatory @Link MainWindow mainWindow;
    private final @Mandatory @Link Object entity;
    private final @Mandatory @Link EntityField entityField;

    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared UiIntegerField integerField;
    private final @Mandatory @Shared List<UiButton> buttons;

    public EntityIntegerFieldValueSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        this.mainWindow = mainWindow;
        this.entity = entity;
        this.entityField = entityField;
        this.label = new UiLabel(entityField.getName());
        this.integerField = new UiIntegerField();
        this.integerField.setEditable(false);
        this.integerField.addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        this.integerField.addKeyListener(new KeyPressedUserEventHandler(this::onKeyPressed));
        this.integerField.addFocusListener(new FocusLostUserEventHandler(this::onFocusLost));
        this.buttons = new List<>(
            new UiButton(mainWindow, IconGallery.CLEAR, null, "Clear", this::onClearButtonClicked),
            new UiButton(mainWindow, IconGallery.EDIT, null, "Edit", this::onEditButtonClicked)
        );
        refresh();
    }

    @Override
    public @Mandatory UiLabel getLabel() {
        return label;
    }

    @Override
    public @Mandatory UiTextField getContent() {
        return integerField;
    }

    @Override
    public @Mandatory List<UiButton> getButtons() {
        return buttons;
    }

    @Override
    public void refresh() {
        Object object = entityField.get(entity);
        integerField.setInteger((Integer)object);
        integerField.setNull(object == null);
    }

    private void setValue(@Optional Object value) {
        mainWindow.getApplicationState().getHistory().run(new SetEntityFieldAction(
            entityField, entity, value, entityField.get(entity)
        ));
        refresh();
    }

    private void onMouseClicked(MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                unlock();
            }
        }
    }

    private void onKeyPressed(KeyEvent event) {
        if(event.getKeyCode() == Key.ENTER){
            setValue(integerField.getInteger());
            lock();
        }

        if(event.getKeyCode() == Key.ESCAPE){
            lock();
        }
    }

    private void onFocusLost() {
        lock();
    }

    private void onClearButtonClicked() {
        setValue(null);
    }

    private void onEditButtonClicked() {
        unlock();
    }

    private void lock(){
        integerField.setEditable(false);
        integerField.getCaret().setVisible(false);
        refresh();
    }

    private void unlock(){
        integerField.setEditable(true);
        integerField.requestFocus();
        integerField.getCaret().setVisible(true);
    }
}
