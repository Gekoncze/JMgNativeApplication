package cz.mg.nativeapplication.sevices.c.creator.command;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CCommand;
import cz.mg.nativeapplication.entities.mg.command.MgBreakCommand;


public @Service class CBreakCommandCreator {
    public CCommand create(MgBreakCommand mgCommand){
        CCommand cCommand = new CCommand();
        if(mgCommand.target != null){
            cCommand.expression = "goto " + mgCommand.target + "_end";
        } else {
            cCommand.expression = "break";
        }
        return cCommand;
    }
}
