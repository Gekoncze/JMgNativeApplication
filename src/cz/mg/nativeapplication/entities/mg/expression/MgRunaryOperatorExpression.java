package cz.mg.nativeapplication.entities.mg.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.entities.mg.components.MgFunction;


public @Entity class MgRunaryOperatorExpression extends MgExpression {
    public @Link MgFunction function;
    public @Part MgExpression left;
}
