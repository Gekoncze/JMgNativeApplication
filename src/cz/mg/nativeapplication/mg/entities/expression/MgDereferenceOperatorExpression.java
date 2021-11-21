package cz.mg.nativeapplication.mg.entities.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.entity.EntityClass;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.entity.validator.common.Required;


public @Mg @Entity class MgDereferenceOperatorExpression extends MgExpression {
    public static EntityClass entity;

    public @Required @Part MgExpression right;
}
