package cz.mg.nativeapplication.gui.services;

import cz.mg.nativeapplication.gui.Initialization;
import cz.mg.nativeapplication.gui.utilities.Navigation;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.entities.components.MgFunction;
import cz.mg.nativeapplication.mg.entities.components.MgLocation;
import cz.mg.nativeapplication.mg.entities.components.MgStructure;
import cz.mg.nativeapplication.mg.entities.components.MgVariable;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class NavigationCreatorTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(NavigationCreatorTest.class);
    }

    @TestCase
    public void test(){
        new Initialization().init();

        MgProject project = new MgProject();
        project.name = "Test Project";
        project.root = new MgLocation();

        MgVariable structureVariable = new MgVariable();
        structureVariable.name = "Test Structure Variable";

        MgStructure structure = new MgStructure();
        structure.name = "Test Structure";
        structure.variables.addLast(structureVariable);

        MgVariable functionVariable = new MgVariable();
        functionVariable.name = "Test Function Variable";

        MgFunction function = new MgFunction();
        function.name = "Test Function";
        function.output = functionVariable;

        project.root.components.addLast(structure);
        project.root.components.addLast(function);

        Navigation navigation = new NavigationCreator().create(project);

        assertNotNull(navigation.getRoot());
        assertNotNull(navigation.get(project));
        assertNotNull(navigation.get(project.root));
        assertNotNull(navigation.get(structure));
        assertNotNull(navigation.get(structureVariable));
        assertNotNull(navigation.get(function));
        assertNotNull(navigation.get(functionVariable));

        assertSame(project, navigation.getRoot().getSelf());
        assertEquals(1, navigation.getRoot().getChildren().count());
        assertSame(navigation.get(project.root), navigation.getRoot().getChildren().getFirst());
        assertNotNull(navigation.get(project.root).getParent());
        assertSame(navigation.get(project.root).getParent(), navigation.getRoot());
    }
}
