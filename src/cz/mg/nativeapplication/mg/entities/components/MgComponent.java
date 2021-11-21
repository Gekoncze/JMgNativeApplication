package cz.mg.nativeapplication.mg.entities.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.EntityClass;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.annotations.Name;
import cz.mg.entity.validator.common.Required;


public abstract @Mg @Entity class MgComponent {
    public static EntityClass entity;

    public @Required @Name @Value String name;
}
