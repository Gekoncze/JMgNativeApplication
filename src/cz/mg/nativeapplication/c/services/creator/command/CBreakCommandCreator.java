package cz.mg.nativeapplication.c.services.creator.command;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.c.entities.CCommand;
import cz.mg.nativeapplication.mg.entities.command.MgBreakCommand;


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
