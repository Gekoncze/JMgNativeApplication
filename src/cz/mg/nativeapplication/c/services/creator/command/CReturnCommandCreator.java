package cz.mg.nativeapplication.c.services.creator.command;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.c.entities.CCommand;
import cz.mg.nativeapplication.mg.entities.command.MgReturnCommand;
import cz.mg.nativeapplication.c.services.creator.expression.CExpressionCreator;


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
