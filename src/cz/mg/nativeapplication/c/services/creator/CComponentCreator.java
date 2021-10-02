package cz.mg.nativeapplication.c.services.creator;

import cz.mg.nativeapplication.c.entities.CFolder;
import cz.mg.nativeapplication.mg.entities.components.*;


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
        if(component instanceof MgLocation){
            MgLocation location = (MgLocation) component;
            if(!location.external){
                folder.folders.addLast(create(location));
            }
            return;
        }

        if(component instanceof MgAtom){
            return; // atoms are handled in c project creator
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
