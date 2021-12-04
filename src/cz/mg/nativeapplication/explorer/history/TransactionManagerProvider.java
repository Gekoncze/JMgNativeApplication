package cz.mg.nativeapplication.explorer.history;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;


public @Service class TransactionManagerProvider {
    private static @Optional @Link TransactionManager instance;

    public void set(@Optional TransactionManager instance){
        TransactionManagerProvider.instance = instance;
    }

    public @Mandatory TransactionManager get(){
        if(instance != null){
            return instance;
        } else {
            throw new IllegalStateException("Missing transaction manager.");
        }
    }
}
