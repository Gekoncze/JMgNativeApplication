package cz.mg.nativeapplication.gui.components.entity.single.part;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.ToStringBuilder;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClasses;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.services.*;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.entities.existing.MgExisting;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.*;
import cz.mg.nativeapplication.gui.components.dialogs.UiConfirmDialog;
import cz.mg.nativeapplication.gui.components.entity.single.EntitySingleSelect;
import cz.mg.nativeapplication.gui.handlers.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;

import java.awt.event.MouseEvent;


public @Utility class EntityFieldPartSelect extends EntitySingleSelect {
    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared UiTextField content;
    private final @Mandatory @Shared List<UiButton> buttons;
    private final @Mandatory @Shared UiPopupMenu popupMenu;

    private final @Mandatory @Shared ObjectNameProvider objectNameProvider = new ObjectNameProvider();
    private final @Mandatory @Shared ClassIconProvider classIconProvider = new ClassIconProvider();
    private final @Mandatory @Shared EntityUsageSearch entityUsageSearch = new EntityUsageSearch();
    private final @Mandatory @Shared EntityPartSearch entityPartSearch = new EntityPartSearch();

    public EntityFieldPartSelect(
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
            MgProject project = mainWindow.getApplicationState().getProject();

            List<EntitySearchResult> usages = entityUsageSearch.search(project, getValue());
            List<EntitySearchResult> parts = entityPartSearch.search(getValue());
            List<EntitySearchResult> partsUsages = new List<>();

            for(EntitySearchResult part : parts){
                partsUsages.addCollectionLast(entityUsageSearch.search(project, part.getEntityField().get(part.getEntity())));
            }

            String title = "Delete entity?";
            List<String> messages = new List<>();
            messages.addLast("Are you sure you want to delete the entity?");
            if(usages.count() > 0) messages.addLast("All references (" + usages.count() + ") to the entity will be set to null.");
            if(parts.count() > 0) messages.addLast("All of the entity parts (" + parts.count() + ") will be deleted recursively.");
            if(partsUsages.count() > 0) messages.addLast("All references (" + partsUsages.count() + ") to the entity parts will be set to null.");
            String message = new ToStringBuilder<>(messages).delim(" ").build();

            UiConfirmDialog.Choice choice = new UiConfirmDialog(title, message).show();
            if(choice == UiConfirmDialog.Choice.YES){
                // todo: wait for searches to be implemented
                // todo: delete this entity and all its references
                // todo: delete this entity parts and all their references
            }
            refresh();
        }
    }

    private void onCreateButtonClicked() {
        Object value = getValue();
        if(value == null){
            EntityClass entityClass = EntityClasses.getRepository().get(entityField.getType());
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
