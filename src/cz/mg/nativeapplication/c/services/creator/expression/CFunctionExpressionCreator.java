package cz.mg.nativeapplication.c.services.creator.expression;

import cz.mg.annotations.classes.Service;
import cz.mg.collections.ToStringBuilder;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.entities.expression.MgExpression;
import cz.mg.nativeapplication.mg.entities.expression.MgFunctionExpression;


public @Service class CFunctionExpressionCreator {
    public String create(MgFunctionExpression mgExpression){
        List<String> arguments = new List<>();
        for(MgExpression mgSubExpression : mgExpression.expressions){
            arguments.addLast(new CExpressionCreator().create(mgSubExpression));
        }
        return mgExpression.function.name + "(" + new ToStringBuilder<>(arguments).delim(", ").build() + ")";
    }
}
