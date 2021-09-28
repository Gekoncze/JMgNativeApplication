package cz.mg.nativeapplication.gui.components.entity.single.part;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassRepository;
import cz.mg.entity.EntityClasses;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.*;
import cz.mg.nativeapplication.gui.components.dialogs.UiConfirmDialog;
import cz.mg.nativeapplication.gui.components.entity.single.EntitySingleSelect;
import cz.mg.nativeapplication.gui.handlers.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.services.ClassIconProvider;
import cz.mg.nativeapplication.gui.services.ObjectNameProvider;
import cz.mg.nativeapplication.mg.entities.existing.MgExisting;
import cz.mg.nativeapplication.mg.services.explorer.DeleteService;

import java.awt.event.MouseEvent;


public @Utility class EntityPartSingleSelect extends EntitySingleSelect {
    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared UiTextField content;
    private final @Mandatory @Shared List<UiButton> buttons;
    private final @Mandatory @Shared UiPopupMenu popupMenu;

    private final @Mandatory @Shared ObjectNameProvider objectNameProvider = new ObjectNameProvider();
    private final @Mandatory @Shared ClassIconProvider classIconProvider = new ClassIconProvider();
    private final @Mandatory @Shared DeleteService deleteService = new DeleteService();
    private final @Mandatory @Shared EntityClassRepository entityClassRepository = EntityClasses.getRepository();

    public EntityPartSingleSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        super(mainWindow, entity, entityField);
        this.label = new UiLabel(entityField.getName());
        this.content = new UiTextField();
        this.content.setEditable(false);
        this.content.addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        this.buttons = new List<>(
            new UiButton(mainWindow, IconGallery.CREATE, null, "Create", this::onCreateButtonClicked),
            new UiButton(mainWindow, IconGallery.EDIT, null, "Edit", this::onEditButtonClicked),
            new UiButton(mainWindow, IconGallery.DELETE, null, "Delete", this::onDeleteButtonClicked)
        );
        this.popupMenu = new UiPopupMenu();
        refresh();
    }

    @Override
    public @Mandatory UiLabel getLabel() {
        return label;
    }

    @Override
    public @Mandatory UiTextField getContent() {
        return content;
    }

    @Override
    public @Mandatory List<UiButton> getButtons() {
        return buttons;
    }

    @Override
    public void refresh() {
        Object object = getValue();
        content.setText(objectNameProvider.get(object));
        content.setNull(object == null);
    }

    private void onMouseClicked(MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                onEditButtonClicked();
            }
        }
    }

    private void onDeleteButtonClicked() {
        if(getValue() != null){
            String title = "Delete entity?";
            String message = "Are you sure you want to delete the entity? Shared ownership might still keep it alive.";

            UiConfirmDialog.Choice choice = new UiConfirmDialog(title, message).show();
            if(choice == UiConfirmDialog.Choice.YES){
                mainWindow.getApplicationState().getHistory().add(
                    deleteService.remove(
                        mainWindow.getApplicationState().getProject(),
                        entity,
                        entityField
                    )
                );
            }
            refresh();
        }
    }

    private void onCreateButtonClicked() {
        Object value = getValue();
        if(value == null){
            EntityClass entityClass = entityClassRepository.get(entityField.getType());
            if(entityClass.getSubclasses().count() == 1){
                setValue(entityClass.newInstance());
            } else if(entityClass.getSubclasses().count() > 1) {
                popupMenu.removeAll();
                for(EntityClass option : entityClass.getSubclasses()){
                    if(!MgExisting.class.isAssignableFrom(option.getClazz())){
                        popupMenu.add(new UiMenuItem(
                            classIconProvider.get(option.getClazz()),
                            option.getName(),
                            () -> setValue(option.newInstance())
                        ));
                    }
                }
                popupMenu.show(content, 0, content.getHeight());
            }
        }
    }

    private void onEditButtonClicked() {
        Object child = entityField.get(entity);
        if(child != null){
            mainWindow.getMainView().getMainTabView().open(child);
        }
    }
}
