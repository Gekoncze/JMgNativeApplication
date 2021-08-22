package cz.mg.nativeapplication.mg.entities.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;


public @Entity class MgReferenceOperatorExpression extends MgExpression {
    public @Part MgExpression right;
}
