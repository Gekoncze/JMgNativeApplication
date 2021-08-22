package cz.mg.nativeapplication.c.services.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.c.entities.CFile;
import cz.mg.nativeapplication.c.entities.CFolder;
import cz.mg.nativeapplication.c.entities.CProject;
import cz.mg.nativeapplication.storage.entities.Folder;


public @Service class CProjectExporter {
    public Folder export(CProject cProject){
        Folder folder = createFolder(cProject.root);
        folder.name = cProject.name;
        return folder;
    }

    private Folder createFolder(CFolder cFolder){
        Folder folder = new Folder();
        folder.name = cFolder.name;

        for(CFolder subFolder : cFolder.folders){
            folder.folders.addLast(createFolder(subFolder));
        }

        for(CFile cFile : cFolder.files){
            folder.files.addLast(new CFileExporter().export(cFile));
        }

        return folder;
    }
}
