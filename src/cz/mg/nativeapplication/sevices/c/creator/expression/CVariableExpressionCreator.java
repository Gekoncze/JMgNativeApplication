package cz.mg.nativeapplication.sevices.c.creator.expression;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.mg.expression.MgVariableExpression;


public @Service class CVariableExpressionCreator {
    public String create(MgVariableExpression mgExpression){
        return mgExpression.variable.name;
    }
}
