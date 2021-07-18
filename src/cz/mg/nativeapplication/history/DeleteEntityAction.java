package cz.mg.nativeapplication.history;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public @Utility class DeleteEntityAction implements Action {
    private final @Mandatory @Part SetFieldValueAction parentFieldAction;
    private final @Mandatory @Part List<SetFieldValueAction> referenceFieldActions;

    public DeleteEntityAction(
        @Mandatory SetFieldValueAction parentFieldAction,
        @Mandatory List<SetFieldValueAction> referenceFieldActions
    ) {
        this.parentFieldAction = parentFieldAction;
        this.referenceFieldActions = referenceFieldActions;
    }

    @Override
    public void redo() {
        for(SetFieldValueAction referenceFieldAction : referenceFieldActions){
            referenceFieldAction.redo();
        }
        parentFieldAction.redo();
    }

    @Override
    public void undo() {
        parentFieldAction.undo();
        for(SetFieldValueAction referenceFieldAction : referenceFieldActions){
            referenceFieldAction.undo();
        }
    }
}
