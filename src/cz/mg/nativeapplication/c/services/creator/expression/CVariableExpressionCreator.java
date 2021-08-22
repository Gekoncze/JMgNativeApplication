package cz.mg.nativeapplication.c.services.creator.expression;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.mg.entities.expression.MgVariableExpression;


public @Service class CVariableExpressionCreator {
    public String create(MgVariableExpression mgExpression){
        return mgExpression.variable.name;
    }
}
