package cz.mg.nativeapplication.mg.entities.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.annotations.Required;


public abstract @Mg @Entity class MgBlockCommand extends MgCommand {
    public @Required @Part List<MgCommand> commands = new List<>();
}
