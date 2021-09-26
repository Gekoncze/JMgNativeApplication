package cz.mg.nativeapplication.mg.services.history;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;


public @Utility class AddListItemAction implements Action {
    private final @Mandatory @Link List list;
    private final @Value int index;
    private final @Optional @Link Object item;

    public AddListItemAction(@Mandatory List list, int index, @Optional Object item) {
        this.list = list;
        this.index = index;
        this.item = item;
    }

    @Override
    public void redo() {
        list.add(item, index);
    }

    @Override
    public void undo() {
        list.remove(index);
    }
}
