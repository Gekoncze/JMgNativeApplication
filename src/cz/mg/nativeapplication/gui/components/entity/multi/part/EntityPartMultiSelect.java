package cz.mg.nativeapplication.gui.components.entity.multi.part;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClassRepository;
import cz.mg.entity.EntityClasses;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.controls.UiList;
import cz.mg.nativeapplication.gui.components.entity.multi.EntityMultiSelect;
import cz.mg.nativeapplication.gui.components.popups.EntityClassPopupMenu;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.mg.services.explorer.DeleteService;
import cz.mg.nativeapplication.mg.services.other.CollectionTypeProvider;


public @Utility class EntityPartMultiSelect extends EntityMultiSelect {
    private static final int LIST_BORDER = 2;
    private static final int LIST_PADDING = 2;

    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared UiList content;
    private final @Mandatory @Shared List<UiButton> buttons;
    private final @Mandatory @Shared EntityClassPopupMenu popupMenu;

    private final @Mandatory @Shared EntityClassRepository entityClassRepository = EntityClasses.getRepository();
    private final @Mandatory @Shared CollectionTypeProvider collectionTypeProvider = new CollectionTypeProvider();
    private final @Mandatory @Shared DeleteService deleteService = new DeleteService();

    public EntityPartMultiSelect(
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
            new UiButton(mainWindow, IconGallery.OPEN, null, "Open", this::onOpenButtonClicked),
            new UiButton(mainWindow, IconGallery.DELETE, null, "Delete", this::onDeleteButtonClicked)
        );
        this.popupMenu = new EntityClassPopupMenu(
            entityClassRepository.get(collectionTypeProvider.get(entityField.getField())),
            this::onCreateEntityClass
        );
        refresh();
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

    private void onMoveUpButtonClicked() {
        moveRowUp();
    }

    private void onMoveDownButtonClicked() {
        moveRowDown();
    }

    private void onAddButtonClicked() {
        addRow();
    }

    private void onOpenButtonClicked() {
        Object value = getValue();
        if(value != null){
            mainWindow.getMainView().getMainTabView().open(value);
        }
    }

    private void onDeleteButtonClicked() {
        // TODO - use delete service and ask user for confirmation
//        if(content.getSelectedIndex() != null){
//            deleteService.remove(
//                mainWindow.getApplicationState().getProject(),
//                list,
//                content.getSelectedIndex()
//            );
//            removeRow();
//        }
    }

    private void onCreateEntityClass() {
        // TODO
    }
}
