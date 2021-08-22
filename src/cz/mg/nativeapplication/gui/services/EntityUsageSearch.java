package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.entities.MgProject;


public @Service class EntityUsageSearch {
    public @Mandatory List<EntitySearchResult> search(@Mandatory MgProject project, @Mandatory Object entity){
        return new List<>(); // todo
    }
}
