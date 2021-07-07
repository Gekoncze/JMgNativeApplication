package cz.mg.nativeapplication.entities.mg.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;


public @Entity class MgReferenceOperatorExpression extends MgExpression {
    public @Part MgExpression right;
}
