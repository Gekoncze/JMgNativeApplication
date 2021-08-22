package cz.mg.nativeapplication.mg.entities.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.nativeapplication.mg.Mg;


public @Mg @Entity class MgBreakCommand extends MgCommand {
    public @Value String target;
}
