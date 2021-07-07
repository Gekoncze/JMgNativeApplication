package cz.mg.nativeapplication.sevices.c.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CStructureDeclaration;


public @Service class CStructureDeclarationExporter {
    public String export(CStructureDeclaration structureDeclaration){
        return "struct " + structureDeclaration.name;
    }
}
