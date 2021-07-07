package cz.mg.nativeapplication.sevices.c.creator;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CVariable;
import cz.mg.nativeapplication.entities.mg.parts.MgVariable;


public @Service class CVariableCreator {
    public CVariable create(MgVariable mgVariable){
        CVariable cVariable = new CVariable();
        cVariable.name = mgVariable.name;
        cVariable.type = new CTypeCreator().create(mgVariable);
        return cVariable;
    }
}
