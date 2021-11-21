package cz.mg.nativeapplication.mg.entities.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.entity.validator.common.Required;


public abstract @Mg @Entity class MgBlockCommand extends MgCommand {
    public static EntityClass entity;

    public @Required @Part List<MgCommand> commands = new List<>();
}
