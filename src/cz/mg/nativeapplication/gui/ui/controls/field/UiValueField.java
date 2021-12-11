package cz.mg.nativeapplication.gui.ui.controls.field;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.gui.ui.enums.alignment.UiAlignment;
import cz.mg.nativeapplication.gui.ui.enums.UiFill;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiFieldBase;
import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldBaseFactory;
import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldBaseWrapper;
import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldImageProvider;


public @Utility class UiValueField extends UiField {
    private final @Mandatory @Part UiFieldBaseWrapper wrapper;

    public UiValueField(
        @Mandatory UiFieldImageProvider fieldImageProvider,
        @Mandatory UiFieldBaseFactory fieldBaseFactory
    ) {
        wrapper = new UiFieldBaseWrapper(fieldImageProvider, fieldBaseFactory.create());
        addVertical(wrapper, 1, 1, UiAlignment.LEFT, UiFill.BOTH);
        rebuild();
    }

    public @Mandatory UiFieldBaseWrapper getWrapper(){
        return wrapper;
    }

    public @Mandatory UiFieldBase getBase(){
        return wrapper.getFieldBase();
    }

    public void lock(){
        wrapper.lock();
    }

    public void unlock(){
        wrapper.unlock();
    }

    public boolean isLocked() {
        return wrapper.isLocked();
    }

    public @Optional Object getValue(){
        return wrapper.getValue();
    }

    public void setValue(@Optional Object value){
        wrapper.setValue(value);
    }
}
