package cz.mg.nativeapplication.c.services.exporter;

import cz.mg.collections.list.List;
import cz.mg.nativeapplication.c.entities.CFunctionDefinition;
import cz.mg.nativeapplication.c.entities.CCommand;


public class CFunctionDefinitionExporter {
    public List<String> export(CFunctionDefinition functionDefinition){
        List<String> lines = new List<>();
        lines.addLast(new CFunctionDeclarationExporter().export(functionDefinition.declaration) + " {");
        for(CCommand command : functionDefinition.commands){
            lines.addCollectionLast(new CCommandExporter().export(command, 1));
        }
        lines.addLast("}");
        return lines;
    }
}
