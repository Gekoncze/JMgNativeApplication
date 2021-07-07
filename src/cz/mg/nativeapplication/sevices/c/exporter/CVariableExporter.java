package cz.mg.nativeapplication.sevices.c.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CVariable;


public @Service class CVariableExporter {
    public String export(CVariable variable){
        return new CTypeExporter().export(variable.type) + " " + variable.name;
    }
}
