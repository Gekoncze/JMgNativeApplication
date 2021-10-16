package cz.mg.nativeapplication.gui.components.entity.single.part;

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
import cz.mg.nativeapplication.gui.components.controls.UiTextField;
import cz.mg.nativeapplication.gui.components.dialogs.UiConfirmDialog;
import cz.mg.nativeapplication.gui.components.popups.EntityClassPopupMenu;
import cz.mg.nativeapplication.gui.components.entity.single.EntitySingleSelect;
import cz.mg.nativeapplication.gui.handlers.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.icons.IconGallery;
import cz.mg.nativeapplication.gui.services.ObjectNameProvider;
import cz.mg.nativeapplication.mg.services.explorer.DeleteService;

import java.awt.event.MouseEvent;


public @Utility class EntityPartSingleSelect extends EntitySingleSelect {
    private final @Mandatory @Shared UiLabel label;
    private final @Mandatory @Shared UiTextField content;
    private final @Mandatory @Shared List<UiButton> buttons;
    private final @Mandatory @Shared EntityClassPopupMenu popupMenu;

    private final @Mandatory @Shared ObjectNameProvider objectNameProvider = new ObjectNameProvider();
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
        this.content.addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        this.buttons = new List<>(
            new UiButton(mainWindow, IconGallery.CREATE, null, "Create", this::onCreateButtonClicked),
            new UiButton(mainWindow, IconGallery.OPEN, null, "Open", this::onOpenButtonClicked),
            new UiButton(mainWindow, IconGallery.DELETE, null, "Delete", this::onDeleteButtonClicked)
        );
        this.popupMenu = new EntityClassPopupMenu(
            entityClassRepository.get(entityField.getType()),
            this::onCreateEntityClass
        );
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
                onOpenButtonClicked();
            }
        }
    }

    private void onDeleteButtonClicked() {
        if(getValue() != null){
            String title = "Delete entity?";
            String message = "Are you sure you want to delete the entity? Shared ownership might still keep it alive.";

            UiConfirmDialog.Choice choice = new UiConfirmDialog(title, message).show();
            if(choice == UiConfirmDialog.Choice.YES){
                deleteService.remove(
                    mainWindow.getApplicationState().getHistory().addTransaction(),
                    mainWindow.getApplicationState().getProject(),
                    entity,
                    entityField
                );
                mainWindow.refresh();
            }
        }
    }

    private void onCreateButtonClicked() {
        Object value = getValue();
        if(value == null){
            popupMenu.select(content);
        }
    }

    private void onOpenButtonClicked() {
        Object value = getValue();
        if(value != null){
            mainWindow.getMainView().getMainTabView().open(value);
        }
    }

    private void onCreateEntityClass(){
        setValue(popupMenu.getSelectedEntityClass().newInstance());
        mainWindow.refresh();
    }
}
