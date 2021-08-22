package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;


public @Service class EntityPartSearch {
    public @Mandatory List<EntitySearchResult> search(@Mandatory Object parent){
        return new List<>(); // todo
    }
}
