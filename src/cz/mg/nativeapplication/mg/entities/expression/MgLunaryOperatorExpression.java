package cz.mg.nativeapplication.mg.entities.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.mg.entities.components.MgFunction;


public @Entity class MgLunaryOperatorExpression extends MgExpression {
    public @Link MgFunction function;
    public @Part MgExpression right;
}
