package cz.mg.nativeapplication.sevices.c.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CFile;
import cz.mg.nativeapplication.entities.c.CFolder;
import cz.mg.nativeapplication.entities.c.CProject;
import cz.mg.nativeapplication.entities.storage.Folder;


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
