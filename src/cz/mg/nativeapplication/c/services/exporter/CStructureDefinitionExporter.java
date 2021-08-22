package cz.mg.nativeapplication.c.services.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.c.entities.CStructureDefinition;
import cz.mg.nativeapplication.c.entities.CVariable;


public @Service class CStructureDefinitionExporter {
    public List<String> export(CStructureDefinition structureDefinition){
        List<String> lines = new List<>();
        String declaration = new CStructureDeclarationExporter().export(structureDefinition.declaration);
        lines.addLast(declaration + " {");
        for(CVariable variable : structureDefinition.variables){
            lines.addLast("    " + new CVariableExporter().export(variable) + ";");
        }
        lines.addLast("};");
        return lines;
    }
}
