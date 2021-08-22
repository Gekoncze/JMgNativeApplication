package cz.mg.nativeapplication.mg.entities.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.mg.entities.expression.MgExpression;


public @Entity class MgReturnCommand extends MgCommand {
    public @Part MgExpression expression;
}
