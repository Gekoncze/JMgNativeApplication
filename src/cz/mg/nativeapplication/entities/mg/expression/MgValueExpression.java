package cz.mg.nativeapplication.entities.mg.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.entities.mg.parts.MgValue;


public @Entity class MgValueExpression extends MgExpression {
    public @Part MgValue value;
}
