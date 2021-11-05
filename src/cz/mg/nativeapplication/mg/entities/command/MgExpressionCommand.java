package cz.mg.nativeapplication.mg.entities.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.annotations.Required;
import cz.mg.nativeapplication.mg.entities.expression.MgExpression;


public @Mg @Entity class MgExpressionCommand extends MgCommand {
    public @Required @Part MgExpression expression;
}
