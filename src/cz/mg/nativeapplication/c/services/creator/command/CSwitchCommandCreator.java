package cz.mg.nativeapplication.c.services.creator.command;

import cz.mg.annotations.classes.Service;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.c.entities.CBlockCommand;
import cz.mg.nativeapplication.c.entities.CCommand;
import cz.mg.nativeapplication.mg.entities.command.MgCaseCommand;
import cz.mg.nativeapplication.mg.entities.command.MgCommand;
import cz.mg.nativeapplication.mg.entities.command.MgSwitchCommand;
import cz.mg.nativeapplication.c.services.creator.expression.CExpressionCreator;


public @Service class CSwitchCommandCreator {
    public List<CCommand> create(MgSwitchCommand mgCommand){
        List<CCommand> cCommands = new List<>();
        for(MgCaseCommand mgCaseCommand : mgCommand.cases){
            CBlockCommand cCommand = new CBlockCommand();

            if(mgCaseCommand == mgCommand.cases.getFirst()){
                cCommand.expression = "if(" + new CExpressionCreator().create(mgCaseCommand.condition) + ")";
            } else {
                if(mgCaseCommand.condition != null){
                    cCommand.expression = "else if(" + new CExpressionCreator().create(mgCaseCommand.condition) + ")";
                } else {
                    cCommand.expression = "else";
                }
            }

            for(MgCommand mgSubCommand : mgCaseCommand.commands){
                cCommand.commands.addCollectionLast(
                    new CCommandCreator().create(mgSubCommand)
                );
            }

            cCommands.addLast(cCommand);
        }
        return cCommands;
    }
}
