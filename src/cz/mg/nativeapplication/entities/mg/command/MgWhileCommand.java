package cz.mg.nativeapplication.entities.mg.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.nativeapplication.entities.mg.expression.MgExpression;


public @Entity class MgWhileCommand extends MgBlockCommand {
    public @Value String name;
    public @Part MgExpression condition;
}
