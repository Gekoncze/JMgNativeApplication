package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.EntityClass;


public @Entity class CType {
    public static EntityClass entity;

    public @Value Boolean structure;
    public @Value String name;
    public @Value Integer pointers;
}
