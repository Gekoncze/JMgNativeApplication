package cz.mg.nativeapplication.mg.entities.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.expression.MgExpression;


public @Mg @Entity class MgCaseCommand extends MgBlockCommand {
    public @Part MgExpression condition;
}
