package cz.mg.nativeapplication.sevices.c.creator.command;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CCommand;
import cz.mg.nativeapplication.entities.mg.command.MgReturnCommand;
import cz.mg.nativeapplication.sevices.c.creator.expression.CExpressionCreator;


public @Service class CReturnCommandCreator {
    public CCommand create(MgReturnCommand mgCommand){
        CCommand cCommand = new CCommand();
        cCommand.expression = "return";
        if(mgCommand.expression != null){
            cCommand.expression += " " + new CExpressionCreator().create(mgCommand.expression);
        }
        return cCommand;
    }
}
