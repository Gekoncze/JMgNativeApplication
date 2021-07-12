package cz.mg.nativeapplication.history;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.ArrayList;
import cz.mg.collections.list.List;


public @Utility class History {
    private final @Value int limit;
    private final @Mandatory @Part List<Action> actions = new ArrayList<>();
    private @Value int position = -1;

    public History(int limit) {
        this.limit = limit;
    }

    public void add(@Mandatory Action action){
        trimRight();
        actions.addLast(action);
        trimLeft();
    }

    public void redo(){
        if((position + 1) < actions.count()){
            actions.get(position + 1).redo();
            position++;
        }
    }

    public void undo(){
        if(position > -1){
            actions.get(position).undo();
            position--;
        }
    }

    public void clear(){
        actions.clear();
    }

    private void trimRight(){
        while((position + 1) > actions.count() && !actions.isEmpty()){
            actions.removeLast();
        }
    }

    private void trimLeft(){
        while(this.actions.count() > limit && !actions.isEmpty()){
            this.actions.removeFirst();
        }
    }
}
