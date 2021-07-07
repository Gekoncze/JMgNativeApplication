package cz.mg.nativeapplication;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.storage.Folder;
import cz.mg.nativeapplication.sevices.storage.StorageSaver;

import java.nio.file.Path;
import java.nio.file.Paths;


public @Service class TempStorageSaver {
    public static final Path PATH = Paths.get(
        "/mnt/6398fafc-fe88-4de2-8158-49374d4f4218/DATA/Private/Dev/Java/JMgNativeApplication/temp/test"
    );

    private final StorageSaver storageSaver = new StorageSaver();

    public TempStorageSaver() {
    }

    public void save(Folder folder){
        storageSaver.save(PATH, folder);
    }
}
