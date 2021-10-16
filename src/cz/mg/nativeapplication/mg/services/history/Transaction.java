package cz.mg.nativeapplication.mg.services.history;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;


public @Utility class Transaction {
    private final @Mandatory @Part List<Action> actions = new List<>();

    Transaction() {
    }

    public void run(@Mandatory Action action) {
        actions.addLast(action);
        action.redo();
    }

    void redo() {
        for(Action action : actions){
            action.redo();
        }
    }

    void undo() {
        for(
            ListItem<Action> actionItem = actions.getLastItem();
            actionItem != null;
            actionItem = actionItem.getPreviousItem()
        ){
            Action action = actionItem.get();
            action.undo();
        }
    }
}
