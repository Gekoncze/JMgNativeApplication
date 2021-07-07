package cz.mg.nativeapplication.sevices.c.exporter;

import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.c.CFunctionDefinition;
import cz.mg.nativeapplication.entities.c.CCommand;


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
