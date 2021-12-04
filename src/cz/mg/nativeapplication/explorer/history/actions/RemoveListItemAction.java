package cz.mg.nativeapplication.explorer.history.actions;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.explorer.history.Action;


public @Utility class RemoveListItemAction implements Action {
    private final @Mandatory @Link List list;
    private final @Value int index;
    private final @Optional @Link Object item;

    public RemoveListItemAction(@Mandatory List list, int index, @Optional Object item) {
        this.list = list;
        this.index = index;
        this.item = item;
    }

    @Override
    public void redo() {
        list.remove(index);
    }

    @Override
    public void undo() {
        list.add(item, index);
    }
}
