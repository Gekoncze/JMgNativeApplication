package cz.mg.nativeapplication.entities.mg.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.entities.mg.components.MgVariable;


public @Entity class MgVariableExpression extends MgExpression {
    public @Link MgVariable variable;
}
