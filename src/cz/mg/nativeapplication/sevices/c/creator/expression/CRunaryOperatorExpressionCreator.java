package cz.mg.nativeapplication.sevices.c.creator.expression;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.mg.expression.MgRunaryOperatorExpression;


public @Service class CRunaryOperatorExpressionCreator {
    public String create(MgRunaryOperatorExpression mgExpression){
        String left = new CExpressionCreator().create(mgExpression.left);
        String operator = mgExpression.function.operator.signs;
        return "(" + left + " " + operator + ")";
    }
}
