package cz.mg.nativeapplication.mg.entities.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.entities.components.MgFunction;


public @Entity class MgFunctionExpression extends MgExpression {
    public @Link MgFunction function;
    public @Part List<MgExpression> expressions = new List<>();
}
