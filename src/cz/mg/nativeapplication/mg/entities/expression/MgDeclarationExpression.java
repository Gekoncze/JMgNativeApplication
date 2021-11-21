package cz.mg.nativeapplication.mg.entities.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.entity.EntityClass;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.entity.validator.common.Required;
import cz.mg.nativeapplication.mg.entities.components.MgVariable;


public @Mg @Entity class MgDeclarationExpression extends MgExpression {
    public static EntityClass entity;

    public @Required @Part MgVariable variable;
}
