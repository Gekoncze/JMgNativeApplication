package cz.mg.nativeapplication.gui.ui.controls.field;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldBaseFactory;
import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldBaseWrapper;
import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldImageProvider;


public @Utility class UiValueField extends UiField {
    private final @Mandatory @Part UiFieldBaseWrapper field;

    public UiValueField(
        @Mandatory UiFieldImageProvider fieldImageProvider,
        @Mandatory UiFieldBaseFactory fieldBaseFactory
    ) {
        field = new UiFieldBaseWrapper(fieldImageProvider, fieldBaseFactory.create());
        add(field, 1, 1, Alignment.LEFT, Fill.BOTH);
        rebuild();
    }

    public @Mandatory UiFieldBaseWrapper getField() {
        return field;
    }

    public void lock(){
        field.lock();
    }

    public void unlock(){
        field.unlock();
    }

    public boolean isLocked() {
        return field.isLocked();
    }

    public @Optional Object getValue(){
        return field.getValue();
    }

    public void setValue(@Optional Object value){
        field.setValue(value);
    }
}
