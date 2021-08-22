package cz.mg.nativeapplication.storage.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.storage.entities.File;
import cz.mg.nativeapplication.storage.entities.Folder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public @Service class StorageSaver {
    public void save(@Mandatory Path root, @Mandatory Folder folder){
        checkCyclesBeforeWritingToStorage(folder);
        try {
            Path folderPath = Paths.get(root.toString(), folder.name);
            deleteIfExists(folderPath);
            Files.createDirectories(folderPath);

            for(File file : folder.files){
                save(folderPath, file);
            }

            for(Folder subfolder : folder.folders){
                save(folderPath, subfolder);
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void save(@Mandatory Path root, @Mandatory File file){
        try {
            Path filePath = Paths.get(root.toString(), file.name);
            Files.deleteIfExists(filePath);
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))){
                for(String line : file.lines){
                    if(line.contains("\n") || line.contains("\r")) throw new IllegalStateException("Unexpected new line.");
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private void checkCyclesBeforeWritingToStorage(@Mandatory Folder folder){
        for(Folder subfolder : folder.folders){
            checkCyclesBeforeWritingToStorage(subfolder);
        }
    }

    private void deleteIfExists(@Mandatory Path path){
        deleteIfExists(path.toFile());
    }

    private void deleteIfExists(@Mandatory java.io.File file){
        if(!Files.isSymbolicLink(file.toPath())){
            java.io.File[] subfiles = file.listFiles();
            if(subfiles != null){
                for(java.io.File subfile : subfiles){
                    deleteIfExists(subfile);
                }
            }
        }
        file.delete();
    }
}
