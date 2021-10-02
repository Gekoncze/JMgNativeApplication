package cz.mg.nativeapplication.mg.services.creator;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.mg.entities.components.MgComponent;
import cz.mg.nativeapplication.mg.entities.components.MgLocation;


public @Service class MgLocationCreator {
    public MgLocation create(MgLocation root, String... names){
        return create(root, 0, names);
    }

    private MgLocation create(MgLocation parent, int i, String... names){
        String name = names[i];

        for(MgComponent child : parent.components){
            if(child instanceof MgLocation){
                MgLocation location = (MgLocation) child;
                if(name.equals(location.name)){
                    if(i >= names.length - 1){
                        return location;
                    } else {
                        return create(location, i+1, names);
                    }
                }
            }
        }

        MgLocation location = new MgLocation();
        location.name = name;
        location.external = false;

        parent.components.addLast(location);

        if(i >= names.length - 1){
            return location;
        } else {
            return create(location, i+1, names);
        }
    }
}
