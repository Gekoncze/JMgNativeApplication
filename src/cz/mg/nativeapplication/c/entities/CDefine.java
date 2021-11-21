package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;


public @Entity class CDefine implements CComponent {
    public static EntityClass entity;

    public @Value String name;
    public @Value List<String> parameters = new List<>();
    public @Value String definition;
}
