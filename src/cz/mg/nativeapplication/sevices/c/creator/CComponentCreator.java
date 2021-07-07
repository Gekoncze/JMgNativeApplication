package cz.mg.nativeapplication.sevices.c.creator;

import cz.mg.nativeapplication.entities.c.CFolder;
import cz.mg.nativeapplication.entities.mg.components.*;
import cz.mg.nativeapplication.entities.mg.existing.MgExisting;


public class CComponentCreator {
    public CFolder create(MgLocation location){
        CFolder folder = new CFolder();
        folder.name = location.name;

        for(MgComponent component : location.components){
            addComponent(folder, component);
        }

        return folder;
    }

    private void addComponent(CFolder folder, MgComponent component){
        if(component instanceof MgExisting){
            return; // existing (usually external or native) components don't need to be (re)created
        }

        if(component instanceof MgLocation){
            MgLocation location = (MgLocation) component;
            folder.folders.addLast(create(location));
            return;
        }

        if(component instanceof MgAtom){
            return; // atoms are handled in c project creator
        }

        if(component instanceof MgClass){
            return; // todo;
        }

        if(component instanceof MgStructure){
            MgStructure structure = (MgStructure) component;
            new CStructureCreator().create(folder, structure);
            return;
        }

        if(component instanceof MgFunction){
            MgFunction function = (MgFunction) component;
            new CFunctionCreator().create(folder, function);
            return;
        }

        throw new UnsupportedOperationException("Mg component of type " + component.getClass().getSimpleName() + " is not supported in C component creator.");
    }
}
