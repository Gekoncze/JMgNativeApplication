package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;


public @Entity class CFunctionDeclaration implements CComponent {
    public static EntityClass entity;

    public @Value String name;
    public @Part List<CVariable> input = new List<>();
    public @Part CType output;
}
