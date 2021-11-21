package cz.mg.nativeapplication.mg.entities.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.EntityClass;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.entity.validator.common.Required;
import cz.mg.nativeapplication.mg.entities.annotations.Whole;


public @Mg @Entity class MgVariable extends MgComponent {
    public static EntityClass entity;

    public @Required @Link MgType type;
    public @Required @Whole @Value Integer pointers;
}
