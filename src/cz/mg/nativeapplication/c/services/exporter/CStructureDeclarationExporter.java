package cz.mg.nativeapplication.c.services.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.c.entities.CStructureDeclaration;


public @Service class CStructureDeclarationExporter {
    public String export(CStructureDeclaration structureDeclaration){
        return "struct " + structureDeclaration.name;
    }
}
