package cz.mg.nativeapplication.c.services.creator.expression;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.mg.entities.expression.MgBinaryOperatorExpression;


public @Service class CBinaryOperatorExpressionCreator {
    public String create(MgBinaryOperatorExpression mgExpression){
        String left = new CExpressionCreator().create(mgExpression.left);
        String operator = mgExpression.function.operator.signs;
        String right = new CExpressionCreator().create(mgExpression.right);
        return "(" + left + " " + operator + " " + right + ")";
    }
}
