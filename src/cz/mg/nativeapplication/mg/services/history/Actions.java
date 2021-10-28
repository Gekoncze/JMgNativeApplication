package cz.mg.nativeapplication.mg.services.history;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;


public @Utility class Actions {
    private static @Mandatory @Shared TransactionManagerProvider transactionManagerProvider = new TransactionManagerProvider();

    public static void run(@Optional Action action){
        if(action != null){
            transactionManagerProvider.get().run(action);
        }
    }
}
