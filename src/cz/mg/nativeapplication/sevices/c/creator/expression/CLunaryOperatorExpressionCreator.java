package cz.mg.nativeapplication.sevices.c.creator.expression;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.mg.expression.MgLunaryOperatorExpression;


public @Service class CLunaryOperatorExpressionCreator {
    public String create(MgLunaryOperatorExpression mgExpression){
        String operator = mgExpression.function.operator.signs;
        String right = new CExpressionCreator().create(mgExpression.right);
        return "(" + operator + " " + right + ")";
    }
}
