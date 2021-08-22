package cz.mg.nativeapplication.c.services.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.c.entities.CTypedef;


public @Service class CTypedefExporter {
    public String export(CTypedef typedef){
        return "typedef " + typedef.type + " " + typedef.alias;
    }
}
