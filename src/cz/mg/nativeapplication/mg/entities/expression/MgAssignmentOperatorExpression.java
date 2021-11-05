package cz.mg.nativeapplication.mg.entities.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.annotations.Required;


public @Mg @Entity class MgAssignmentOperatorExpression extends MgExpression {
    public @Required @Part MgExpression left;
    public @Required @Part MgExpression right;
}
