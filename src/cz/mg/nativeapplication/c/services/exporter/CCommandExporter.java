package cz.mg.nativeapplication.c.services.exporter;

import cz.mg.annotations.classes.Service;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.c.entities.CBlockCommand;
import cz.mg.nativeapplication.c.entities.CCommand;


public @Service class CCommandExporter {
    public List<String> export(CCommand command, int level){
        List<String> lines = new List<>();
        String indentation = level(level);

        StringBuilder header = new StringBuilder();
        if(command.expression != null){
            header.append(command.expression);
        }

        if(command instanceof CBlockCommand) {
            lines.addLast(indentation + header.append(" {").toString());
            for(CCommand subcommand : ((CBlockCommand) command).commands){
                lines.addCollectionLast(new CCommandExporter().export(subcommand, level + 1));
            }
            lines.addLast(indentation + "}");
        } else {
            lines.addLast(indentation + header.append(";").toString());
        }
        return lines;
    }

    public String level(int level){
        StringBuilder indentation = new StringBuilder();
        for(int i = 0; i < level; i++){
            indentation.append("    ");
        }
        return indentation.toString();
    }
}
