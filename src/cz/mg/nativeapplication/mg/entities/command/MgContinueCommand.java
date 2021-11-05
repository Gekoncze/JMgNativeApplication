package cz.mg.nativeapplication.mg.entities.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.annotations.Name;
import cz.mg.nativeapplication.mg.entities.annotations.Required;


public @Mg @Entity class MgContinueCommand extends MgCommand {
    public @Required @Name @Value String target;
}
