package cz.mg.nativeapplication.mg.entities.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.annotations.Required;
import cz.mg.nativeapplication.mg.entities.components.MgFunction;


public @Mg @Entity class MgRunaryOperatorExpression extends MgExpression {
    public @Required @Link MgFunction function;
    public @Required @Part MgExpression left;
}
