package other;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.storage.entities.Folder;
import cz.mg.nativeapplication.storage.services.StorageSaver;

import static all.Configuration.TEMP_PATH;


public @Service class TempStorageSaver {
    private final StorageSaver storageSaver = new StorageSaver();

    public TempStorageSaver() {
    }

    public void save(Folder folder){
        storageSaver.save(TEMP_PATH, folder);
    }
}
