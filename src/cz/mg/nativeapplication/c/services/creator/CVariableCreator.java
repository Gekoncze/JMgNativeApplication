package cz.mg.nativeapplication.c.services.creator;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.c.entities.CVariable;
import cz.mg.nativeapplication.mg.entities.components.MgVariable;


public @Service class CVariableCreator {
    public CVariable create(MgVariable mgVariable){
        CVariable cVariable = new CVariable();
        cVariable.name = mgVariable.name;
        cVariable.type = new CTypeCreator().create(mgVariable);
        return cVariable;
    }
}
