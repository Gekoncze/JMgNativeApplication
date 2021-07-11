package cz.mg.nativeapplication.sevices.c.creator;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CType;
import cz.mg.nativeapplication.entities.mg.components.MgStructure;
import cz.mg.nativeapplication.entities.mg.components.MgVariable;


public @Service class CTypeCreator {
    public CType create(MgVariable variable){
        CType type = new CType();
        type.structure = variable.type instanceof MgStructure;
        type.name = variable.type.name;
        type.pointers = variable.pointers;
        return type;
    }
}
