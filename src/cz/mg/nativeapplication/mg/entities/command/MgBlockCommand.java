package cz.mg.nativeapplication.mg.entities.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public abstract @Entity class MgBlockCommand extends MgCommand {
    public @Part List<MgCommand> commands = new List<>();
}
