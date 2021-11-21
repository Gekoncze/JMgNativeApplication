package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.EntityClass;


public @Entity class CVariable {
    public static EntityClass entity;

    public @Part CType type;
    public @Value String name;
}
