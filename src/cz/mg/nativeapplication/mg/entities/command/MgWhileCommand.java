package cz.mg.nativeapplication.mg.entities.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.expression.MgExpression;


public @Mg @Entity class MgWhileCommand extends MgBlockCommand {
    public @Value String name;
    public @Part MgExpression condition;
}
