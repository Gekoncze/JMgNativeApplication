package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;


public @Entity class CProject {
    public static EntityClass entity;

    public @Value String name;
    public @Value Boolean library;
    public @Part CFolder root;
    public @Value List<String> libraries = new List<>();
}
