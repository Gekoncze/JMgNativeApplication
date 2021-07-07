package cz.mg.nativeapplication.sevices.c.creator;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CFolder;


public @Service class CFolderCreator {
    public CFolder create(CFolder root, String... names){
        return create(root, 0, names);
    }

    private CFolder create(CFolder parent, int i, String... names){
        String name = names[i];

        for(CFolder child : parent.folders){
            if(name.equals(child.name)){
                if(i >= names.length - 1){
                    return child;
                } else {
                    return create(child, i+1, names);
                }
            }
        }

        CFolder folder = new CFolder();
        folder.name = name;
        parent.folders.addLast(folder);

        if(i >= names.length - 1){
            return folder;
        } else {
            return create(folder, i+1, names);
        }
    }
}
