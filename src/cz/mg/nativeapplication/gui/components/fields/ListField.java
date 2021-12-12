package cz.mg.nativeapplication.gui.components.fields;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.explorer.Explorer;
import cz.mg.nativeapplication.gui.ui.controls.UiList;
import cz.mg.nativeapplication.gui.ui.controls.UiText;


public @Utility class ListField extends ObjectField {
    private final @Mandatory @Shared UiText label;
    private final @Mandatory @Shared UiList list;

    public ListField(
        @Mandatory Explorer explorer,
        @Mandatory Object object,
        int index,
        @Mandatory Class type,
        @Mandatory String label
    ) {
        super(explorer, object, index, type);
        this.label = new UiText(label);
        this.list = new UiList();
    }

    @Override
    public void refresh() {
        // TODO
    }
}
