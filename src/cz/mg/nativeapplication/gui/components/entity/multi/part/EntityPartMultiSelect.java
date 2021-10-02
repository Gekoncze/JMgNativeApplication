package cz.mg.nativeapplication.gui.components.entity.multi.part;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.controls.UiList;
import cz.mg.nativeapplication.gui.components.entity.multi.EntityMultiSelect;
import cz.mg.nativeapplication.gui.icons.IconGallery;


public @Utility class EntityPartMultiSelect extends EntityMultiSelect {
    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared UiList content;
    private final @Mandatory @Shared List<UiButton> buttons;

    public EntityPartMultiSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        super(mainWindow, entity, entityField);
        this.label = new UiLabel(entityField.getName());
        this.content = new UiList();
        this.buttons = new List<>(
            new UiButton(mainWindow, IconGallery.CREATE, null, "Add", this::onAddButtonClicked),
            new UiButton(mainWindow, IconGallery.EDIT, null, "Edit", this::onEditButtonClicked),
            new UiButton(mainWindow, IconGallery.DELETE, null, "Delete", this::onDeleteButtonClicked),
            new UiButton(mainWindow, IconGallery.UP, null, "Move up", this::onMoveUpButtonClicked),
            new UiButton(mainWindow, IconGallery.DOWN, null, "Move down", this::onMoveDownButtonClicked)
        );
        // TODO
    }

    @Override
    public UiLabel getLabel() {
        return label;
    }

    @Override
    public UiList getContent() {
        return content;
    }

    @Override
    public List<UiButton> getButtons() {
        return buttons;
    }

    @Override
    protected @Mandatory Object createValue() {
        // TODO
        return null;
    }

    @Override
    protected void editValue(@Mandatory Object object) {
        // TODO
    }

    private void onAddButtonClicked() {
        if(content.getSelectedIndex() >= 0){
            // TODO
        } else {
            // TODO
        }
    }

    private void onEditButtonClicked() {
        if(content.getSelectedIndex() >= 0){
            // TODO
        }
    }

    private void onDeleteButtonClicked() {
        if(content.getSelectedIndex() >= 0){
            // TODO
        }
    }

    private void onMoveUpButtonClicked() {
        if(content.getSelectedIndex() >= 0){
            // TODO
        }
    }

    private void onMoveDownButtonClicked() {
        if(content.getSelectedIndex() >= 0){
            // TODO
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        // TODO
    }
}
