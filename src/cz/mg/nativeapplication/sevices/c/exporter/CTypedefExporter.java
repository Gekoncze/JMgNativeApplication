package cz.mg.nativeapplication.sevices.c.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CTypedef;


public @Service class CTypedefExporter {
    public String export(CTypedef typedef){
        return "typedef " + typedef.type + " " + typedef.alias;
    }
}
