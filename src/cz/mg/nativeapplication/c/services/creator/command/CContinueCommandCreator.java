package cz.mg.nativeapplication.c.services.creator.command;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.c.entities.CCommand;
import cz.mg.nativeapplication.mg.entities.command.MgContinueCommand;


public @Service class CContinueCommandCreator {
    public CCommand create(MgContinueCommand mgCommand){
        CCommand cCommand = new CCommand();
        if(mgCommand.target != null){
            cCommand.expression = "goto " + mgCommand.target + "_begin";
        } else {
            cCommand.expression = "continue";
        }
        return cCommand;
    }
}
