package cz.mg.nativeapplication.mg.entities.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.entity.EntityClass;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.entity.validator.common.Required;
import cz.mg.nativeapplication.mg.entities.parts.MgValue;


public @Mg @Entity class MgValueExpression extends MgExpression {
    public static EntityClass entity;

    public @Required @Part MgValue value;
}
