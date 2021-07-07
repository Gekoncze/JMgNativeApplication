package cz.mg.nativeapplication.sevices.c.creator.command;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CBlockCommand;
import cz.mg.nativeapplication.entities.c.CCommand;
import cz.mg.nativeapplication.entities.mg.command.MgCommand;
import cz.mg.nativeapplication.entities.mg.command.MgWhileCommand;
import cz.mg.nativeapplication.sevices.c.creator.expression.CExpressionCreator;


public @Service class CWhileCommandCreator {
    public CCommand create(MgWhileCommand mgCommand){
        CBlockCommand cCommand = new CBlockCommand();
        cCommand.expression = "while(" + new CExpressionCreator().create(mgCommand.condition) + ")";

        if(mgCommand.name != null){
            cCommand.expression = mgCommand.name + "_begin: " + cCommand.expression;
        }

        for(MgCommand mgSubCommand : mgCommand.commands){
            cCommand.commands.addCollectionLast(
                new CCommandCreator().create(mgSubCommand)
            );

            if(mgCommand.name != null){
                cCommand.commands.addLast(createSimpleCommand(mgCommand.name + "_end:"));
            }
        }

        return cCommand;
    }

    private CCommand createSimpleCommand(String expression){
        CCommand command = new CCommand();
        command.expression = expression;
        return command;
    }
}
