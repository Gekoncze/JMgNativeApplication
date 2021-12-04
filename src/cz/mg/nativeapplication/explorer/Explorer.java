package cz.mg.nativeapplication.explorer;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.explorer.history.TransactionManager;


public @Utility class Explorer {
    private final @Mandatory @Part TransactionManager transactionManager = new TransactionManager();

    public Explorer() {
    }

    public @Mandatory TransactionManager getTransactionManager() {
        return transactionManager;
    }
}
