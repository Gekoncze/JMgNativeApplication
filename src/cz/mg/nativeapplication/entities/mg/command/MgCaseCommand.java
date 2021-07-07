package cz.mg.nativeapplication.entities.mg.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.entities.mg.expression.MgExpression;


public @Entity class MgCaseCommand extends MgBlockCommand {
    public @Part MgExpression condition;
}
