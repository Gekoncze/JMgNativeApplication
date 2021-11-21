package cz.mg.nativeapplication.c.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;


public @Entity class CFunctionDefinition implements CComponent {
    public static EntityClass entity;

    public @Shared CFunctionDeclaration declaration;
    public @Part List<CCommand> commands = new List<>();
}
