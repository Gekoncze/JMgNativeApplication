package cz.mg.nativeapplication.mg.services.history;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.ArrayList;
import cz.mg.collections.list.List;


public @Utility class History {
    private final @Value int limit;
    private final @Mandatory @Part List<Transaction> transactions = new ArrayList<>();
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

    public int count() {
        return transactions.count();
    }

    public @Mandatory Transaction addTransaction(){
        Transaction transaction = new Transaction();
        trimRight();
        transactions.addLast(transaction);
        trimLeft();
        position++;
        return transaction;
    }

    public void redo(){
        if((position + 1) < transactions.count()){
            transactions.get(position + 1).redo();
            position++;
        }
    }

    public void undo(){
        if(position > -1){
            transactions.get(position).undo();
            position--;
        }
    }

    public void clear(){
        transactions.clear();
    }

    private void trimRight(){
        while((position + 1) < transactions.count() && !transactions.isEmpty()){
            transactions.removeLast();
        }
    }

    private void trimLeft(){
        while(transactions.count() > limit && !transactions.isEmpty()){
            transactions.removeFirst();
            position--;
        }
    }
}
