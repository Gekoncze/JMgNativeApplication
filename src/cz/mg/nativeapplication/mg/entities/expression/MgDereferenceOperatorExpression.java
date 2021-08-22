package cz.mg.nativeapplication.mg.entities.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.mg.Mg;


public @Mg @Entity class MgDereferenceOperatorExpression extends MgExpression {
    public @Part MgExpression right;
}
