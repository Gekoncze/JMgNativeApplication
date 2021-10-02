package other;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.storage.entities.Folder;
import cz.mg.nativeapplication.storage.services.StorageSaver;

import java.nio.file.Path;
import java.nio.file.Paths;


public @Service class TempStorageSaver {
    public static final Path PATH = Paths.get("/home/me/Temporary/test");

    private final StorageSaver storageSaver = new StorageSaver();

    public TempStorageSaver() {
    }

    public void save(Folder folder){
        storageSaver.save(PATH, folder);
    }
}
