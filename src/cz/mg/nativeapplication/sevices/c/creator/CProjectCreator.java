package cz.mg.nativeapplication.sevices.c.creator;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.*;
import cz.mg.nativeapplication.entities.mg.MgProject;
import cz.mg.nativeapplication.sevices.mg.creator.MgProjectCreator;


public @Service class CProjectCreator {
    public CProject create(MgProject mgProject){
        CProject cProject = new CProject();
        cProject.name = mgProject.name;
        cProject.library = mgProject.main == null;
        cProject.libraries.addCollectionLast(mgProject.nativeLibraries);

        cProject.root = new CComponentCreator().create(mgProject.root);

        if(mgProject.main != null){
            cProject.root.files.addLast(
                new CMainCreator().create(mgProject.main)
            );
        }

        CFolder atomsFolder = new CFolderCreator().create(cProject.root, MgProjectCreator.ATOMS_LOCATION);
        atomsFolder.files.addLast(new CAtomCreator().create());

        return cProject;
    }
}
