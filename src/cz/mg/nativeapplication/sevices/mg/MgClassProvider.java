package cz.mg.nativeapplication.sevices.mg;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;


/**
 * @see MgClassProviderGenerator
 **/
public @Service class MgClassProvider {
    public @Mandatory List<Class> get(){
        try {
            return new List<>(
                Class.forName("cz.mg.nativeapplication.entities.mg.existing.MgExistingStructure"),
                Class.forName("cz.mg.nativeapplication.entities.mg.existing.MgExistingLocation"),
                Class.forName("cz.mg.nativeapplication.entities.mg.existing.MgExistingFunction"),
                Class.forName("cz.mg.nativeapplication.entities.mg.existing.MgExisting"),
                Class.forName("cz.mg.nativeapplication.entities.mg.existing.MgExistingInterface"),
                Class.forName("cz.mg.nativeapplication.entities.mg.expression.MgLunaryOperatorExpression"),
                Class.forName("cz.mg.nativeapplication.entities.mg.expression.MgDereferenceOperatorExpression"),
                Class.forName("cz.mg.nativeapplication.entities.mg.expression.MgFunctionExpression"),
                Class.forName("cz.mg.nativeapplication.entities.mg.expression.MgRunaryOperatorExpression"),
                Class.forName("cz.mg.nativeapplication.entities.mg.expression.MgDeclarationExpression"),
                Class.forName("cz.mg.nativeapplication.entities.mg.expression.MgValueExpression"),
                Class.forName("cz.mg.nativeapplication.entities.mg.expression.MgBinaryOperatorExpression"),
                Class.forName("cz.mg.nativeapplication.entities.mg.expression.MgMemberExpression"),
                Class.forName("cz.mg.nativeapplication.entities.mg.expression.MgVariableExpression"),
                Class.forName("cz.mg.nativeapplication.entities.mg.expression.MgAssignmentOperatorExpression"),
                Class.forName("cz.mg.nativeapplication.entities.mg.expression.MgReferenceOperatorExpression"),
                Class.forName("cz.mg.nativeapplication.entities.mg.expression.MgExpression"),
                Class.forName("cz.mg.nativeapplication.entities.mg.MgProject"),
                Class.forName("cz.mg.nativeapplication.entities.mg.command.MgExpressionCommand"),
                Class.forName("cz.mg.nativeapplication.entities.mg.command.MgReturnCommand"),
                Class.forName("cz.mg.nativeapplication.entities.mg.command.MgCaseCommand"),
                Class.forName("cz.mg.nativeapplication.entities.mg.command.MgSwitchCommand"),
                Class.forName("cz.mg.nativeapplication.entities.mg.command.MgBlockCommand"),
                Class.forName("cz.mg.nativeapplication.entities.mg.command.MgWhileCommand"),
                Class.forName("cz.mg.nativeapplication.entities.mg.command.MgBreakCommand"),
                Class.forName("cz.mg.nativeapplication.entities.mg.command.MgContinueCommand"),
                Class.forName("cz.mg.nativeapplication.entities.mg.command.MgCommand"),
                Class.forName("cz.mg.nativeapplication.entities.mg.components.MgAtom"),
                Class.forName("cz.mg.nativeapplication.entities.mg.components.MgInterface"),
                Class.forName("cz.mg.nativeapplication.entities.mg.components.MgFunction"),
                Class.forName("cz.mg.nativeapplication.entities.mg.components.MgLocation"),
                Class.forName("cz.mg.nativeapplication.entities.mg.components.MgStructure"),
                Class.forName("cz.mg.nativeapplication.entities.mg.components.MgVariable"),
                Class.forName("cz.mg.nativeapplication.entities.mg.components.MgComponent"),
                Class.forName("cz.mg.nativeapplication.entities.mg.components.MgType"),
                Class.forName("cz.mg.nativeapplication.entities.mg.parts.MgValue"),
                Class.forName("cz.mg.nativeapplication.entities.mg.parts.MgOperator")
            );
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
