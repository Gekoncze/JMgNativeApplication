package cz.mg.nativeapplication.gui.event;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.gui.ui.dialogs.UiErrorMessageDialog;
import cz.mg.nativeapplication.gui.services.ApplicationProvider;
import cz.mg.entity.explorer.history.TransactionManager;


public abstract @Utility class EventHandler {
    private final @Mandatory @Shared ApplicationProvider applicationProvider = new ApplicationProvider();

    public void run(@Mandatory Runnable runnable){
        try {
            TransactionManager transactionManager = applicationProvider.get().getExplorer().getTransactionManager();
            if(transactionManager.isTransactionInProgress()){
                runnable.run();
            } else {
                transactionManager.transaction(runnable);
            }
        } catch (RuntimeException e){
            new UiErrorMessageDialog(e).show();
        }
    }
}
