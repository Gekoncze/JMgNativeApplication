package cz.mg.nativeapplication.gui.components.fields.value;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.explorer.Explorer;
import cz.mg.nativeapplication.gui.components.fields.ValueField;
import cz.mg.nativeapplication.gui.components.popups.EnumPopupMenu;
import cz.mg.nativeapplication.gui.ui.controls.UiPopupMenu;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiEnumFieldBase;


public @Utility class EnumValueField extends ValueField {
    private final @Mandatory @Shared UiPopupMenu popupMenu;

    public EnumValueField(
        @Mandatory Explorer explorer,
        @Mandatory Object object,
        int index,
        @Mandatory Class<? extends Enum> type,
        @Mandatory String label
    ) {
        super(explorer, object, index, type, label, () -> new UiEnumFieldBase<>(type));
        this.popupMenu = new EnumPopupMenu(type, this::setValue);
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
