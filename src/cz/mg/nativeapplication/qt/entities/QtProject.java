package cz.mg.nativeapplication.qt.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;


public @Entity class QtProject {
    public static EntityClass entity;

    public @Value String name;
    public @Part List<QtConfig> settings = new List<>();
}
