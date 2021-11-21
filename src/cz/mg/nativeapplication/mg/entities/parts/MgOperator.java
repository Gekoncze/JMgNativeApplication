package cz.mg.nativeapplication.mg.entities.parts;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.EntityClass;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.entity.validator.common.Required;


public @Mg @Entity class MgOperator {
    public static EntityClass entity;

    public @Required @Value MgOperatorType type;
    public @Required @Value String signs;
}
