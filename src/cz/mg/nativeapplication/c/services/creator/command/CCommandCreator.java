package cz.mg.nativeapplication.c.services.creator.command;

import cz.mg.annotations.classes.Service;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.c.entities.CCommand;
import cz.mg.nativeapplication.mg.entities.command.*;


public @Service class CCommandCreator {
    public List<CCommand> create(MgCommand mgCommand){
        if(mgCommand instanceof MgBreakCommand){
            return new List<>(
                new CBreakCommandCreator().create((MgBreakCommand) mgCommand)
            );
        }

        if(mgCommand instanceof MgContinueCommand){
            return new List<>(
                new CContinueCommandCreator().create((MgContinueCommand) mgCommand)
            );
        }

        if(mgCommand instanceof MgExpressionCommand){
            return new List<>(
                new CExpressionCommandCreator().create((MgExpressionCommand) mgCommand)
            );
        }

        if(mgCommand instanceof MgReturnCommand){
            return new List<>(
                new CReturnCommandCreator().create((MgReturnCommand) mgCommand)
            );
        }

        if(mgCommand instanceof MgSwitchCommand){
            return new CSwitchCommandCreator().create((MgSwitchCommand) mgCommand);
        }

        if(mgCommand instanceof MgWhileCommand){
            return new List<>(
                new CWhileCommandCreator().create((MgWhileCommand) mgCommand)
            );
        }

        throw new UnsupportedOperationException("Unsupported command " + mgCommand.getClass().getSimpleName() + " in c command creator.");
    }
}
