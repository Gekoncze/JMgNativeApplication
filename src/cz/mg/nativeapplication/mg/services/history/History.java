package cz.mg.nativeapplication.mg.services.history;

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

    public int getLimit() {
        return limit;
    }

    public int getPosition() {
        return position;
    }

    public int getCount(){
        return actions.count();
    }

    public void run(@Mandatory Action action){
        add(action);
        action.redo();
    }

    public void add(@Mandatory Action action){
        trimRight();
        actions.addLast(action);
        trimLeft();
        position++;
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
        while((position + 1) < actions.count() && !actions.isEmpty()){
            actions.removeLast();
        }
    }

    private void trimLeft(){
        while(actions.count() > limit && !actions.isEmpty()){
            actions.removeFirst();
            position--;
        }
    }
}
