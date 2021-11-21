package cz.mg.nativeapplication.mg.entities.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.entity.EntityClass;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.entity.validator.common.Required;
import cz.mg.nativeapplication.mg.entities.expression.MgExpression;


public @Mg @Entity class MgExpressionCommand extends MgCommand {
    public static EntityClass entity;

    public @Required @Part MgExpression expression;
}
