package cz.mg.nativeapplication.gui.components.entity.single.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.*;
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

import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.LEFT;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.MIDDLE;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.BOTH;


public @Utility class EntityIntegerFieldValueSelect implements EntitySingleSelect {
    private static final int PADDING = 2;

    private final @Mandatory @Link MainWindow mainWindow;
    private final @Mandatory @Link Object entity;
    private final @Mandatory @Link EntityField entityField;

    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared UiIntegerField integerField;
    private final @Mandatory @Shared UiPanel buttons;
    private final @Mandatory @Shared UiButton clearButton;

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
        this.clearButton = new UiButton(mainWindow, IconGallery.CLEAR, null, "Clear", this::onClearButtonClicked);
        this.buttons = new UiPanel(0, PADDING, LEFT);
        this.buttons.add(clearButton, 0, 0, 0, 0, MIDDLE, BOTH);
        this.buttons.rebuild();
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
    public @Mandatory UiPanel getButtons() {
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
