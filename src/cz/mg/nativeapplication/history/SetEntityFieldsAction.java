package cz.mg.nativeapplication.history;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public @Utility class SetEntityFieldsAction implements Action {
    private final @Mandatory @Part List<SetEntityFieldAction> referenceFieldActions;

    public SetEntityFieldsAction(@Mandatory List<SetEntityFieldAction> referenceFieldActions) {
        this.referenceFieldActions = referenceFieldActions;
    }

    @Override
    public void redo() {
        for(SetEntityFieldAction referenceFieldAction : referenceFieldActions){
            referenceFieldAction.redo();
        }
    }

    @Override
    public void undo() {
        for(SetEntityFieldAction referenceFieldAction : referenceFieldActions){
            referenceFieldAction.undo();
        }
    }
}
