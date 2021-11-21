package cz.mg.nativeapplication.mg.entities.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.EntityClass;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.annotations.Name;
import cz.mg.entity.validator.common.Required;


public @Mg @Entity class MgBreakCommand extends MgCommand {
    public static EntityClass entity;

    public @Required @Name @Value String target;
}
