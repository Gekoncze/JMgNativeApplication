package cz.mg.nativeapplication.mg.entities.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.entity.validator.common.Required;


public @Mg @Entity class MgInterface extends MgType {
    public static EntityClass entity;

    public @Required @Part List<MgVariable> input = new List<>();
    public @Part List<MgVariable> output;
}
