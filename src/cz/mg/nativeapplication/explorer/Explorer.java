package cz.mg.nativeapplication.explorer;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.explorer.history.TransactionManager;


public @Utility class Explorer {
    private final @Mandatory @Part TransactionManager transactionManager = new TransactionManager();
    private final @Mandatory @Shared Provider rootProvider;

    public Explorer(@Mandatory Provider rootProvider) {
        this.rootProvider = rootProvider;
    }

    public @Mandatory TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public @Mandatory Provider getRootProvider() {
        return rootProvider;
    }

    public interface Provider {
        @Mandatory Object get();
    }
}
