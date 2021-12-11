package cz.mg.nativeapplication.gui.components.fields;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassProvider;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.explorer.services.UpdateService;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySelectContent;
import cz.mg.nativeapplication.gui.components.entity.content.EntitySingleSelectContent;
import cz.mg.nativeapplication.gui.components.popups.EntityClassPopupMenu;
import cz.mg.nativeapplication.gui.components.fields.ObjectField;
import cz.mg.nativeapplication.gui.event.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.images.ImageGallery;
import cz.mg.nativeapplication.gui.services.ApplicationProvider;
import cz.mg.nativeapplication.gui.ui.controls.UiButton;
import cz.mg.nativeapplication.gui.ui.controls.UiText;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiObjectFieldBase;
import cz.mg.nativeapplication.gui.ui.dialogs.UiConfirmDialog;

import java.awt.event.MouseEvent;
import java.util.Objects;


public @Utility class PartField extends ObjectField {
    private final @Mandatory @Shared ApplicationProvider applicationProvider = new ApplicationProvider();
    private final @Mandatory @Shared UpdateService updateService = new UpdateService();
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();

    private final @Mandatory @Shared UiText label;
    private final @Mandatory @Shared EntitySelectContent content;
    private final @Mandatory @Shared UiButton createButton;
    private final @Mandatory @Shared UiButton openButton;
    private final @Mandatory @Shared UiButton deleteButton;
    private final @Mandatory @Shared EntityClassPopupMenu popupMenu;

    public PartField(@Mandatory Object entity, @Mandatory EntityField entityField) {
        this.content = new EntitySingleSelectContent(entity, entityField, this::createContentField);
        this.label = new UiText(entityField.getName(), UiText.FontStyle.BOLD);
        this.createButton = new UiButton(ImageGallery.CREATE, null, "Create", this::onCreateButtonClicked);
        this.openButton = new UiButton(ImageGallery.OPEN, null, "Open", this::onOpenButtonClicked);
        this.deleteButton = new UiButton(ImageGallery.DELETE, null, "Delete", this::onDeleteButtonClicked);
        this.popupMenu = new EntityClassPopupMenu(
            entityClassProvider.get(content.getType()),
            this::onCreateEntityClass
        );
        addHorizontal(label, 0, 0, Alignment.MIDDLE, Fill.BOTH);
        addHorizontal(content.getField(), 0, 0, Alignment.MIDDLE, Fill.BOTH);
        addHorizontal(createButton, 0, 0, Alignment.MIDDLE, Fill.BOTH);
        addHorizontal(openButton, 0, 0, Alignment.MIDDLE, Fill.BOTH);
        addHorizontal(deleteButton, 0, 0, Alignment.MIDDLE, Fill.BOTH);
        rebuild();
        refresh();
    }

    @Override
    public void refresh() {
        content.refresh();
    }

    private UiObjectFieldBase createContentField(){
        UiObjectFieldBase objectField = new UiObjectFieldBase();
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

    private void onCreateButtonClicked() {
        if(content.getFieldBase() != null){
            Object value = content.getValue();
            if(value == null){
                popupMenu.select(content.getFieldBase());
            }
        }
    }

    private void onOpenButtonClicked() {
        Object value = content.getValue();
        if(value != null){
            applicationProvider.get().getMainWindow().getMainView().getMainTabView().open(value);
        }
    }

    private boolean onDeleteButtonClicked() {
        if(content.getValue() != null){
            String title = "Delete entity?";
            String message = "Are you sure you want to delete the entity? Shared ownership might still keep it alive.";

            UiConfirmDialog.Choice choice = new UiConfirmDialog(title, message).show();
            if(choice == UiConfirmDialog.Choice.YES){
                updateService.update(
                    applicationProvider.get().getExplorer(),
                    content.getParent(),
                    Objects.requireNonNull(content.getChildIndex()),
                    null
                );
                applicationProvider.get().getMainWindow().refresh();
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private void onCreateEntityClass(EntityClass entityClass){
        content.setValue(entityClass.newInstance());
        applicationProvider.get().getMainWindow().refresh();
    }
}
