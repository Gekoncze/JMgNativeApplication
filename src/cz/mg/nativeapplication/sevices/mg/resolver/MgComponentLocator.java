package cz.mg.nativeapplication.sevices.mg.resolver;

import cz.mg.annotations.classes.Service;
import cz.mg.collections.ToStringBuilder;
import cz.mg.collections.array.Array;
import cz.mg.nativeapplication.entities.mg.components.MgComponent;
import cz.mg.nativeapplication.entities.mg.components.MgLocation;
import cz.mg.nativeapplication.entities.mg.MgProject;


public @Service class MgComponentLocator {
    public MgComponent find(MgProject project, String... path){
        if(path.length < 1) return project.root;
        return find(project.root, path, 0);
    }

    private MgComponent find(MgLocation location, String[] path, int i){
        String wanted = path[i];
        for(MgComponent component : location.components){
            if(component.name.equals(wanted)){
                if(i == (path.length - 1)){
                    return component;
                } else {
                    if(component instanceof MgLocation){
                        return find((MgLocation) component, path, i+1);
                    } else {
                        throw new UnsupportedOperationException("Cannot access sub-component in component of type " + component.getClass().getSimpleName() + ".");
                    }
                }
            }
        }
        throw new RuntimeException("Could not find component '" + new ToStringBuilder(new Array(path)).delim(".").build() + "' at level " + i + ".");
    }
}
