package cz.mg.entity.explorer;

import all.Preparation;
import cz.mg.entity.explorer.services.DeleteService;
import cz.mg.nativeapplication.gui.Initialization;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.entities.command.MgExpressionCommand;
import cz.mg.nativeapplication.mg.entities.components.MgFunction;
import cz.mg.nativeapplication.mg.entities.components.MgLocation;
import cz.mg.nativeapplication.mg.entities.components.MgStructure;
import cz.mg.nativeapplication.mg.entities.components.MgVariable;
import cz.mg.nativeapplication.mg.entities.expression.MgMemberExpression;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class DeleteServiceTest implements Test {
    public static void main(String[] args) {
        new Preparation().prepare();
        new SingleTestClassRunner().run(DeleteServiceTest.class);
    }

    @TestCase(order = 1)
    public void testDelete(){
        MgProject project = new MgProject();
        MgLocation root = new MgLocation();
        project.root = root;
        MgStructure structure = new MgStructure();
        MgVariable field = new MgVariable();
        structure.variables.addLast(field);
        MgFunction function = new MgFunction();
        MgVariable output = new MgVariable();
        function.output = output;
        output.type = structure;
        root.components.addLast(structure);
        root.components.addLast(function);
        MgExpressionCommand command = new MgExpressionCommand();
        function.commands.addLast(command);
        MgMemberExpression expression = new MgMemberExpression();
        command.expression = expression;
        expression.child = field;

        assertSame(root, project.root);
        assertContains(root.components, function);
        assertSame(output, function.output);
        assertContains(function.commands, command);
        assertEquals(expression, command.expression);
        assertContains(root.components, structure);
        assertSame(structure, output.type);
        assertSame(field, expression.child);

        Explorer explorer = new Explorer(MgProject.entity, new Initialization().createMapper());
        explorer.setProject(project);
        explorer.getTransactionManager().transaction(() -> {
            new DeleteService().delete(explorer, structure);
        });

        assertSame(root, project.root);
        assertContains(root.components, function);
        assertSame(output, function.output);
        assertContains(function.commands, command);
        assertEquals(expression, command.expression);
        assertNotContains(root.components, structure);
        assertNull(output.type);
        assertNull(expression.child);
    }

    @TestCase(order = 2)
    public void testDeleteShared(){
        MgProject project = new MgProject();
        project.name = "TestProject";
        MgLocation root = new MgLocation();
        project.root = root;
        MgStructure structure = new MgStructure();
        MgVariable field = new MgVariable();
        structure.variables.addLast(field);
        MgFunction function = new MgFunction();
        MgVariable output = new MgVariable();
        function.output = output;
        output.type = structure;
        root.components.addLast(structure);
        root.components.addLast(field); // shared ownership of field
        root.components.addLast(function);
        MgExpressionCommand command = new MgExpressionCommand();
        function.commands.addLast(command);
        MgMemberExpression expression = new MgMemberExpression();
        command.expression = expression;
        expression.child = field;

        assertSame(root, project.root);
        assertContains(root.components, function);
        assertSame(output, function.output);
        assertContains(function.commands, command);
        assertEquals(expression, command.expression);
        assertContains(root.components, structure);
        assertContains(root.components, field);
        assertSame(structure, output.type);
        assertSame(field, expression.child);

        Explorer explorer = new Explorer(MgProject.entity, new Initialization().createMapper());
        explorer.setProject(project);
        explorer.getTransactionManager().transaction(() -> {
            new DeleteService().delete(explorer, structure);
        });

        assertSame(root, project.root);
        assertContains(root.components, function);
        assertSame(output, function.output);
        assertContains(function.commands, command);
        assertEquals(expression, command.expression);
        assertNotContains(root.components, structure);
        assertContains(root.components, field);
        assertNull(output.type);
        assertSame(field, expression.child);
    }
}
