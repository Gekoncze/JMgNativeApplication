package cz.mg.nativeapplication.c.services.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.c.entities.CVariable;


public @Service class CVariableExporter {
    public String export(CVariable variable){
        return new CTypeExporter().export(variable.type) + " " + variable.name;
    }
}
