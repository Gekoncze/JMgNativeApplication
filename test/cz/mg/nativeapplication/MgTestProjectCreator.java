package cz.mg.nativeapplication;

import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.entities.command.*;
import cz.mg.nativeapplication.mg.entities.components.*;
import cz.mg.nativeapplication.mg.entities.expression.*;
import cz.mg.nativeapplication.mg.entities.parts.MgOperator;
import cz.mg.nativeapplication.mg.entities.parts.MgValue;
import cz.mg.nativeapplication.mg.entities.components.MgVariable;
import cz.mg.nativeapplication.mg.services.MgAtomCreator;
import cz.mg.nativeapplication.mg.services.creator.MgProjectCreator;
import cz.mg.nativeapplication.mg.services.resolver.MgComponentLocator;

import static cz.mg.nativeapplication.mg.services.MgAtomCreator.SMALLER_OPERATOR;


public class MgTestProjectCreator {
    private static final String WHILE_BLOCK_NAME = "loop";

    public MgProject create(){
        MgProject project = new MgProjectCreator().create("MgTestApplication");

        MgStructure structure = new MgStructure();
        structure.name = "TestStructure";
        structure.memoryManagement = true;
        structure.variables.addLast(createVariable(getNumericType(project), 0, "count"));
        structure.variables.addLast(createVariable(structure, 1, "next"));

        project.root.components.addLast(structure);

        MgVariable i = createVariable(getNumericType(project), 0, "i");
        MgFunction function = new MgFunction();
        function.name = "fooBar";
        function.output = createVariable(getNumericType(project), 0, "");
        function.input.addLast(createVariable(getNumericType(project), 0, "foo"));
        function.input.addLast(createVariable(getNumericType(project), 0, "bar"));
        function.commands.addLast(createExpressionCommand(
            createAssignmentExpression(
                createDeclarationExpression(i),
                createValueExpression(project, "0")
            )
        ));
        function.commands.addLast(createWhileCommand(
            WHILE_BLOCK_NAME,
            createBinaryOperatorExpression(
                getNumericBinaryOperator(project, SMALLER_OPERATOR),
                createVariableExpression(i),
                createValueExpression(project, "10")
            ),
            createSwitchCommand(
                createBinaryOperatorExpression(
                    getNumericBinaryOperator(project, SMALLER_OPERATOR),
                    createVariableExpression(i),
                    createValueExpression(project, "5")
                ),
                createContinueCommand(WHILE_BLOCK_NAME),
                createBreakCommand(WHILE_BLOCK_NAME)
            )
        ));
        function.commands.addLast(createWhileCommand(
            null,
            createValueExpression(project, "0"),
            createBreakCommand(null),
            createContinueCommand(null)
        ));
        function.commands.addLast(createReturnCommand(createValueExpression(project, null)));

        project.root.components.addLast(function);
        return project;
    }

    private static MgBreakCommand createBreakCommand(String target){
        MgBreakCommand command = new MgBreakCommand();
        command.target = target;
        return command;
    }

    private static MgContinueCommand createContinueCommand(String target){
        MgContinueCommand command = new MgContinueCommand();
        command.target = target;
        return command;
    }

    private static MgCaseCommand createCaseCommand(MgExpression condition, MgCommand subCommand){
        MgCaseCommand command = new MgCaseCommand();
        command.condition = condition;
        command.commands.addLast(subCommand);
        return command;
    }

    private static MgSwitchCommand createSwitchCommand(MgExpression condition, MgCommand trueBranch, MgCommand falseBranch){
        MgSwitchCommand command = new MgSwitchCommand();
        command.cases.addLast(createCaseCommand(condition, trueBranch));
        command.cases.addLast(createCaseCommand(null, falseBranch));
        return command;
    }

    private static MgVariableExpression createVariableExpression(MgVariable variable){
        MgVariableExpression expression = new MgVariableExpression();
        expression.variable = variable;
        return expression;
    }

    private static MgBinaryOperatorExpression createBinaryOperatorExpression(MgFunction function, MgExpression left, MgExpression right){
        MgBinaryOperatorExpression expression = new MgBinaryOperatorExpression();
        expression.function = function;
        expression.left = left;
        expression.right = right;
        return expression;
    }

    private static MgAssignmentOperatorExpression createAssignmentExpression(MgExpression left, MgExpression right){
        MgAssignmentOperatorExpression expression = new MgAssignmentOperatorExpression();
        expression.left = left;
        expression.right = right;
        return expression;
    }

    private static MgDeclarationExpression createDeclarationExpression(MgVariable variable){
        MgDeclarationExpression expression = new MgDeclarationExpression();
        expression.variable = variable;
        return expression;
    }

    private static MgValueExpression createValueExpression(MgProject project, String value){
        MgValueExpression valueExpression = new MgValueExpression();
        valueExpression.value = new MgValue();
        valueExpression.value.type = getNumericType(project);
        valueExpression.value.value = value;
        return valueExpression;
    }

    private static MgReturnCommand createReturnCommand(MgExpression expression){
        MgReturnCommand returnCommand = new MgReturnCommand();
        returnCommand.expression = expression;
        return returnCommand;
    }

    private static MgWhileCommand createWhileCommand(String name, MgExpression condition, MgCommand... commands){
        MgWhileCommand command = new MgWhileCommand();
        command.condition = condition;
        command.commands = new List<>(commands);
        command.name = name;
        return command;
    }

    private static MgExpressionCommand createExpressionCommand(MgExpression expression){
        MgExpressionCommand command = new MgExpressionCommand();
        command.expression = expression;
        return command;
    }

    private static MgVariable createVariable(MgType type, int pointers, String name){
        MgVariable variable = new MgVariable();
        variable.type = type;
        variable.pointers = pointers;
        variable.name = name;
        return variable;
    }

    private static MgType getNumericType(MgProject project){
        MgLocation atomsLocation = (MgLocation) new MgComponentLocator().find(project, "cz", "mg", "atoms");
        List<MgComponent> atomComponents = atomsLocation.components;

        for(MgComponent atomComponent : atomComponents){
            if(atomComponent instanceof MgAtom){
                MgAtom atom = (MgAtom) atomComponent;
                if(atom.name.equals(MgAtomCreator.UINT32_NAME)){
                    return atom;
                }
            }
        }

        throw new IllegalStateException("Could not find " + MgAtomCreator.UINT32_NAME + " type.");
    }

    private static MgFunction getNumericBinaryOperator(MgProject project, String operator){
        MgLocation atomsLocation = (MgLocation) new MgComponentLocator().find(project, "cz", "mg", "atoms", "operators");
        List<MgComponent> atomComponents = atomsLocation.components;

        for(MgComponent atomComponent : atomComponents){
            if(atomComponent instanceof MgFunction){
                MgFunction function = (MgFunction) atomComponent;
                if(function.operator != null){
                    if(function.operator.type == MgOperator.Type.BINARY){
                        if(function.operator.signs.equals(operator)){
                            if(function.input.getFirst().type.name.equals(MgAtomCreator.UINT32_NAME)){
                                return function;
                            }
                        }
                    }
                }
            }
        }

        throw new IllegalStateException("Could not find binary " + MgAtomCreator.UINT32_NAME + " " + operator + " operator.");
    }
}
