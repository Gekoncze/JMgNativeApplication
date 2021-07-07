package cz.mg.nativeapplication.sevices.c.creator.command;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CCommand;
import cz.mg.nativeapplication.entities.mg.command.MgContinueCommand;


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
