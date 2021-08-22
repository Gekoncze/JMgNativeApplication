package cz.mg.nativeapplication.c.services.creator.command;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.c.entities.CCommand;
import cz.mg.nativeapplication.mg.entities.command.MgExpressionCommand;
import cz.mg.nativeapplication.c.services.creator.expression.CExpressionCreator;


public @Service class CExpressionCommandCreator {
    public CCommand create(MgExpressionCommand mgCommand){
        CCommand cCommand = new CCommand();
        cCommand.expression = new CExpressionCreator().create(mgCommand.expression);
        return cCommand;
    }
}
