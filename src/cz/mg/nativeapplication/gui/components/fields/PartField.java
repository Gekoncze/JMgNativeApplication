package cz.mg.nativeapplication.gui.components.fields;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassProvider;
import cz.mg.nativeapplication.explorer.Explorer;
import cz.mg.nativeapplication.explorer.services.DeleteService;
import cz.mg.nativeapplication.explorer.services.OwnershipService;
import cz.mg.nativeapplication.gui.components.popups.EntityClassPopupMenu;
import cz.mg.nativeapplication.gui.event.MouseClickUserEventHandler;
import cz.mg.nativeapplication.gui.images.ImageGallery;
import cz.mg.nativeapplication.gui.services.MainWindowProvider;
import cz.mg.nativeapplication.gui.services.ObjectImageProvider;
import cz.mg.nativeapplication.gui.services.ObjectNameProvider;
import cz.mg.nativeapplication.gui.ui.controls.UiButton;
import cz.mg.nativeapplication.gui.ui.controls.UiText;
import cz.mg.nativeapplication.gui.ui.controls.field.UiValueField;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiObjectFieldBase;
import cz.mg.nativeapplication.gui.ui.dialogs.UiConfirmDialog;
import cz.mg.nativeapplication.gui.ui.enums.UiFill;
import cz.mg.nativeapplication.gui.ui.enums.alignment.UiAlignment;

import java.awt.event.MouseEvent;


public @Utility class PartField extends ObjectField {
    private final @Mandatory @Shared MainWindowProvider mainWindowProvider = new MainWindowProvider();
    private final @Mandatory @Shared ObjectImageProvider objectImageProvider = new ObjectImageProvider();
    private final @Mandatory @Shared ObjectNameProvider objectNameProvider = new ObjectNameProvider();
    private final @Mandatory @Shared DeleteService deleteService = new DeleteService();
    private final @Mandatory @Shared OwnershipService ownershipService = new OwnershipService();
    private final @Mandatory @Shared EntityClassProvider entityClassProvider = new EntityClassProvider();

    private final @Mandatory @Shared UiText label;
    private final @Mandatory @Shared UiValueField field;
    private final @Mandatory @Shared UiButton createButton;
    private final @Mandatory @Shared UiButton openButton;
    private final @Mandatory @Shared UiButton deleteButton;
    private final @Mandatory @Shared EntityClassPopupMenu popupMenu;

    public PartField(
        @Mandatory Explorer explorer,
        @Mandatory Object object,
        int index,
        @Mandatory Class type,
        @Mandatory String label
    ) {
        super(explorer, object, index, type);
        this.label = new UiText(label, UiText.FontStyle.BOLD);
        this.field = new UiValueField(objectImageProvider::getOptional, UiObjectFieldBase::new);
        this.field.getBase().addMouseListener(new MouseClickUserEventHandler(this::onMouseClicked));
        this.createButton = new UiButton(ImageGallery.CREATE, null, "Create", this::onCreateButtonClicked);
        this.openButton = new UiButton(ImageGallery.OPEN, null, "Open", this::onOpenButtonClicked);
        this.deleteButton = new UiButton(ImageGallery.DELETE, null, "Delete", this::onDeleteButtonClicked);
        this.popupMenu = new EntityClassPopupMenu(entityClassProvider.get(type), this::onCreate);
        addHorizontal(this.label, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.field, 1, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.createButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.openButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        addHorizontal(this.deleteButton, 0, 0, UiAlignment.MIDDLE, UiFill.BOTH);
        rebuild();
        refresh();
    }

    private void onMouseClicked(MouseEvent event) {
        if(event.getButton() == MouseEvent.BUTTON1){
            if(event.getClickCount() == 2){
                onOpenButtonClicked();
            }
        }
    }

    private void onCreateButtonClicked() {
        Object value = getValue();
        if(value == null){
            popupMenu.select(field.getBase());
        }
    }

    private void onOpenButtonClicked() {
        Object value = getValue();
        if(value != null){
            mainWindowProvider.get().getMainView().getMainTabView().open(value);
        }
    }

    private void onDeleteButtonClicked() {
        Object value = getValue();
        if(value != null){
            setValue(null);
            if(!ownershipService.hasOwner(getExplorer(), value)){
                UiConfirmDialog.Choice choice = new UiConfirmDialog(
                    "Delete entity?",
                    "Would you like to delete the object '" + objectNameProvider.get(value) + "'?"
                ).show();

                if(choice == UiConfirmDialog.Choice.YES){
                    deleteService.delete(getExplorer(), value);
                    mainWindowProvider.get().refresh();
                }
            }
        }
    }

    private void onCreate(EntityClass entityClass){
        setValue(entityClass.newInstance());
        mainWindowProvider.get().refresh();
    }

    @Override
    public void refresh() {
        field.setValue(getValue());
        field.lock();
    }
}
