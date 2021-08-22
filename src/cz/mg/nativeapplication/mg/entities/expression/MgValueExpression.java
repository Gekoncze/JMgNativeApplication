package cz.mg.nativeapplication.mg.entities.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.mg.entities.parts.MgValue;


public @Entity class MgValueExpression extends MgExpression {
    public @Part MgValue value;
}
