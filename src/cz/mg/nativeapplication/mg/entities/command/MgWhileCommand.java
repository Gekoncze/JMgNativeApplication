package cz.mg.nativeapplication.mg.entities.command;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.annotations.Name;
import cz.mg.nativeapplication.mg.entities.annotations.Required;
import cz.mg.nativeapplication.mg.entities.expression.MgExpression;


public @Mg @Entity class MgWhileCommand extends MgBlockCommand {
    public @Name @Value String name;
    public @Required @Part MgExpression condition;
}
