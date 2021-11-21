package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.EntityClass;


public @Entity class CCommand {
    public static EntityClass entity;

    public @Value String expression;
}
