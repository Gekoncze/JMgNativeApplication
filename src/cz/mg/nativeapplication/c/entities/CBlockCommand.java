package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;


public @Entity class CBlockCommand extends CCommand {
    public static EntityClass entity;

    public @Part List<CCommand> commands = new List<>();
}
