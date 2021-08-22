package cz.mg.nativeapplication.c.services.creator;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.c.entities.CType;
import cz.mg.nativeapplication.mg.entities.components.MgStructure;
import cz.mg.nativeapplication.mg.entities.components.MgVariable;


public @Service class CTypeCreator {
    public CType create(MgVariable variable){
        CType type = new CType();
        type.structure = variable.type instanceof MgStructure;
        type.name = variable.type.name;
        type.pointers = variable.pointers;
        return type;
    }
}
