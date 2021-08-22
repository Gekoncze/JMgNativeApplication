package cz.mg.nativeapplication.mg.services.resolver;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.mg.entities.components.MgVariable;
import cz.mg.nativeapplication.mg.entities.expression.*;


public @Service class MgExpressionOutputResolver {
    public MgVariable resolve(MgExpression expression){
        if(expression instanceof MgBinaryOperatorExpression){
            return ((MgBinaryOperatorExpression) expression).function.output;
        }

        if(expression instanceof MgLunaryOperatorExpression){
            return ((MgLunaryOperatorExpression) expression).function.output;
        }

        if(expression instanceof MgRunaryOperatorExpression){
            return ((MgRunaryOperatorExpression) expression).function.output;
        }

        if(expression instanceof MgFunctionExpression){
            return ((MgFunctionExpression) expression).function.output;
        }

        if(expression instanceof MgMemberExpression){
            return ((MgMemberExpression) expression).child;
        }

        if(expression instanceof MgVariableExpression){
            return ((MgVariableExpression) expression).variable;
        }

        if(expression instanceof MgValueExpression){
            MgVariable variable = new MgVariable();
            variable.type = ((MgValueExpression) expression).value.type;
            variable.pointers = 0;
            variable.name = "";
            return variable;
        }

        throw new UnsupportedOperationException("Unsupported expression " + expression.getClass().getSimpleName() + " in expression output resolver.");
    }
}
