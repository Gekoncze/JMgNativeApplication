package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassRepository;
import cz.mg.entity.EntityClasses;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.controls.UiButton;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.controls.value.UiObjectField;
import cz.mg.nativeapplication.gui.components.dialogs.UiConfirmDialog;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySelectContent;
import cz.mg.nativeapplication.gui.components.entity.popups.EntityClassPopupMenu;
import cz.mg.nativeapplication.gui.handlers.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.services.MainWindowProvider;
import cz.mg.nativeapplication.mg.services.explorer.DeleteService;

import java.awt.event.MouseEvent;


public @Utility class EntityPartSelect extends EntitySelect {
    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared EntitySelectContent content;
    private final @Mandatory @Shared List<UiButton> buttons;
    private final @Mandatory @Shared EntityClassPopupMenu popupMenu;

    private final @Mandatory @Shared MainWindowProvider mainWindowProvider = new MainWindowProvider();
    private final @Mandatory @Shared DeleteService deleteService = new DeleteService();
    private final @Mandatory @Shared EntityClassRepository entityClassRepository = EntityClasses.getRepository();

    public EntityPartSelect(
        @Mandatory Object entity,
        @Mandatory EntityField entityField,
        @Mandatory EntitySelectType type
    ) {
        this.content = EntitySelectContent.create(entity, entityField, type, this::createContentField);
        this.label = new UiLabel(entityField.getName());
        this.buttons = new List<>(
            new UiButton(IconGallery.CREATE, null, "Create", this::onCreateButtonClicked),
            new UiButton(IconGallery.OPEN, null, "Open", this::onOpenButtonClicked),
            new UiButton(IconGallery.DELETE, null, "Delete", this::onDeleteButtonClicked)
        );
        this.buttons.addCollectionFirst(content.getButtons());
        this.popupMenu = new EntityClassPopupMenu(
            entityClassRepository.get(content.getType()),
            this::onCreateEntityClass
        );
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
    public @Mandatory List<UiButton> getButtons() {
        return buttons;
    }

    @Override
    public void refresh() {
        content.refresh();
    }

    private UiObjectField createContentField(){
        UiObjectField objectField = new UiObjectField();
        objectField.addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        return objectField;
    }

    private void onMouseClicked(MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                onOpenButtonClicked();
            }
        }
    }

    private void onDeleteButtonClicked() {
        if(content.getValue() != null){
            String title = "Delete entity?";
            String message = "Are you sure you want to delete the entity? Shared ownership might still keep it alive.";

            UiConfirmDialog.Choice choice = new UiConfirmDialog(title, message).show();
            if(choice == UiConfirmDialog.Choice.YES){
                deleteService.remove(
                    mainWindowProvider.get().getApplicationState().getHistory().addTransaction(),
                    mainWindowProvider.get().getApplicationState().getProject(),
                    content.getEntity(),
                    content.getEntityField()
                );
                mainWindowProvider.get().refresh();
            }
        }
    }

    private void onCreateButtonClicked() {
        if(content.getField() != null){
            Object value = content.getValue();
            if(value == null){
                popupMenu.select(content.getField());
            }
        }
    }

    private void onOpenButtonClicked() {
        Object value = content.getValue();
        if(value != null){
            mainWindowProvider.get().getMainView().getMainTabView().open(value);
        }
    }

    private void onCreateEntityClass(EntityClass entityClass){
        content.setValue(entityClass.newInstance());
        mainWindowProvider.get().refresh();
    }
}
