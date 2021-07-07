package cz.mg.nativeapplication.sevices.c.creator.command;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CCommand;
import cz.mg.nativeapplication.entities.mg.command.MgExpressionCommand;
import cz.mg.nativeapplication.sevices.c.creator.expression.CExpressionCreator;


public @Service class CExpressionCommandCreator {
    public CCommand create(MgExpressionCommand mgCommand){
        CCommand cCommand = new CCommand();
        cCommand.expression = new CExpressionCreator().create(mgCommand.expression);
        return cCommand;
    }
}
