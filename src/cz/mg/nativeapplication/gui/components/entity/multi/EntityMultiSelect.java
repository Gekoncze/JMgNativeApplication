package cz.mg.nativeapplication.gui.components.entity.multi;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiList;
import cz.mg.nativeapplication.gui.components.entity.EntitySelect;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.mg.services.history.AddListItemAction;
import cz.mg.nativeapplication.mg.services.history.RemoveListItemAction;

import javax.swing.*;


public @Utility abstract class EntityMultiSelect extends EntitySelect {
    protected final @Mandatory @Link MainWindow mainWindow;
    protected final @Mandatory @Link Object entity;
    protected final @Mandatory @Link EntityField entityField;
    protected final @Mandatory @Link List list;

    private final @Mandatory @Shared UiList content;
    private final @Mandatory @Shared List<UiButton> buttons;

    public EntityMultiSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        this.mainWindow = mainWindow;
        this.entity = entity;
        this.entityField = entityField;
        this.list = (List) entityField.get(entity);

        this.content = new UiList();
        this.buttons = new List<>(
            new UiButton(mainWindow, IconGallery.CREATE, null, "Add", this::onAddButtonClicked),
            new UiButton(mainWindow, IconGallery.EDIT, null, "Edit", this::onEditButtonClicked),
            new UiButton(mainWindow, IconGallery.CLEAR, null, "Clear", this::onClearButtonClicked),
            new UiButton(mainWindow, IconGallery.UP, null, "Move up", this::onMoveUpButtonClicked),
            new UiButton(mainWindow, IconGallery.UP, null, "Move down", this::onMoveDownButtonClicked)
        );
    }

    protected final int valueCount(){
        return list.count();
    }

    protected final @Optional Object getValue(int i){
        return list.get(i);
    }

    protected final void addValue(int i, @Optional Object value){
        mainWindow.getApplicationState().getHistory().run(
            new AddListItemAction(list, i, value)
        );
    }

    protected final void removeValue(int i){
        mainWindow.getApplicationState().getHistory().run(
            new RemoveListItemAction(list, i, list.get(i))
        );
    }

    @Override
    public @Mandatory UiList getContent() {
        return content;
    }

    @Override
    public @Mandatory List<UiButton> getButtons() {
        return buttons;
    }

    private void onAddButtonClicked() {
        if(content.getSelectedIndex() >= 0){
            // todo;
        }
    }

    private void onEditButtonClicked() {
        if(content.getSelectedIndex() >= 0){
            // todo;
        }
    }

    private void onClearButtonClicked() {
        if(content.getSelectedIndex() >= 0){
            // todo;
        }
    }

    private void onMoveUpButtonClicked() {
        if(content.getSelectedIndex() >= 0){
            // todo;
        }
    }

    private void onMoveDownButtonClicked() {
        if(content.getSelectedIndex() >= 0){
            // todo;
        }
    }

    private @Mandatory ListModel creteModel(){
        // TODO
        return null;
    }

    private @Mandatory ListCellRenderer createCellRenderer(){
        // TODO
        return null;
    }

    protected abstract @Mandatory Object createValue();
    protected abstract void editValue(@Mandatory Object object);
}
