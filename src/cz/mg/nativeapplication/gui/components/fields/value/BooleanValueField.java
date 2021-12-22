package cz.mg.nativeapplication.gui.components.fields.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.explorer.Explorer;
import cz.mg.nativeapplication.gui.components.fields.ValueField;
import cz.mg.nativeapplication.gui.components.popups.BooleanPopupMenu;
import cz.mg.nativeapplication.gui.ui.controls.UiPopupMenu;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiBooleanFieldBase;


public @Utility class BooleanValueField extends ValueField {
    private final @Mandatory @Shared UiPopupMenu popupMenu;

    public BooleanValueField(
        @Mandatory Explorer explorer,
        @Mandatory Object object,
        int index,
        @Mandatory Class type,
        @Mandatory String label
    ) {
        super(explorer, object, index, checkType(type), label, UiBooleanFieldBase::new);
        this.popupMenu = new BooleanPopupMenu(this::setValue);
    }

    private static @Mandatory Class checkType(@Mandatory Class type){
        if(type == Boolean.class){
            return type;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    protected void onEditButtonClicked() {
        super.onEditButtonClicked();
        popupMenu.show(field.getBase());
    }

    @Override
    protected void onFocusLost() {
        if(!popupMenu.isVisible()){
            super.onFocusLost();
        }
    }
}
