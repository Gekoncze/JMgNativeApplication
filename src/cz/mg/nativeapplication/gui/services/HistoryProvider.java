package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.mg.services.history.History;


public @Service class HistoryProvider {
    private final @Mandatory @Shared MainWindowProvider mainWindowProvider = new MainWindowProvider();

    public @Mandatory History get(){
        History history = mainWindowProvider.get().getApplicationState().getHistory();
        if(history != null){
            return history;
        } else {
            throw new IllegalStateException("Missing history.");
        }
    }
}
