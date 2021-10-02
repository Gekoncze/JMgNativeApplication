package cz.mg.nativeapplication.gui.components.entity.multi.link;

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
import cz.mg.nativeapplication.gui.components.popups.ComponentSearchPopupMenu;
import cz.mg.nativeapplication.gui.icons.IconGallery;


public @Utility class EntityLinkMultiSelect extends EntityMultiSelect {
    private static final int LIST_BORDER = 2;
    private static final int LIST_PADDING = 2;

    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared UiList content;
    private final @Mandatory @Shared List<UiButton> buttons;
    private final @Mandatory @Shared ComponentSearchPopupMenu popupMenu;

    public EntityLinkMultiSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        super(mainWindow, entity, entityField);
        this.label = new UiLabel(entityField.getName());
        this.content = new UiList(LIST_BORDER, LIST_PADDING);
        this.buttons = new List<>(
            new UiButton(mainWindow, IconGallery.UP, null, "Move up", this::onMoveUpButtonClicked),
            new UiButton(mainWindow, IconGallery.DOWN, null, "Move down", this::onMoveDownButtonClicked),
            new UiButton(mainWindow, IconGallery.CREATE, null, "Add", this::onAddButtonClicked),
            new UiButton(mainWindow, IconGallery.SEARCH, null, "Search", this::onSearchButtonClicked),
            new UiButton(mainWindow, IconGallery.OPEN, null, "Open", this::onOpenButtonClicked),
            new UiButton(mainWindow, IconGallery.EDIT, null, "Edit", this::onEditButtonClicked),
            new UiButton(mainWindow, IconGallery.CLEAR, null, "Clear", this::onClearButtonClicked)
        );
        popupMenu = new ComponentSearchPopupMenu(this::onItemSelected);
    }

    @Override
    public @Mandatory UiLabel getLabel() {
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

    private void onMoveUpButtonClicked() {
        if(content.getSelectedIndex() != null){
            // TODO
        }
    }

    private void onMoveDownButtonClicked() {
        if(content.getSelectedIndex() != null){
            // TODO
        }
    }

    private void onAddButtonClicked() {
        int i = content.getSelectedIndex() != null ? content.getSelectedIndex() : valueCount();
        addValue(i, null);
    }

    private void onSearchButtonClicked(){
        if(content.getSelectedIndex() != null){
            // TODO
        }
    }

    private void onOpenButtonClicked(){
        if(content.getSelectedIndex() != null){
            // TODO
        }
    }

    private void onEditButtonClicked() {
        if(content.getSelectedIndex() != null){
            // TODO
        }
    }

    private void onClearButtonClicked() {
        if(content.getSelectedIndex() != null){
            removeValue(content.getSelectedIndex());
        }
    }

    private void onItemSelected(){
        // TODO
    }

    @Override
    public void refresh() {
        super.refresh();
        // TODO
    }
}
