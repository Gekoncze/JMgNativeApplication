package cz.mg.nativeapplication.entities.mg.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.entities.mg.parts.MgVariable;


public @Entity class MgMemberExpression extends MgExpression {
    public @Part MgExpression parent;
    public @Link MgVariable child;
}
