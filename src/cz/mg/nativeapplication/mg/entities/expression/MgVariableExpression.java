package cz.mg.nativeapplication.mg.entities.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.components.MgVariable;


public @Mg @Entity class MgVariableExpression extends MgExpression {
    public @Link MgVariable variable;
}
