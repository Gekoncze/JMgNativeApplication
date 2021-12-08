package cz.mg.nativeapplication.explorer;

import cz.mg.collections.list.List;
import cz.mg.nativeapplication.explorer.services.SearchService;
import cz.mg.nativeapplication.explorer.utilities.Node;
import cz.mg.nativeapplication.gui.Initialization;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.entities.components.*;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class SearchServiceTest implements Test {
    public static void main(String[] args) {
        new Initialization().init();
        new SingleTestClassRunner().run(SearchServiceTest.class);
    }

    @TestCase(order = 0)
    public void testObjectFount(){
        MgProject project = new MgProject();
        project.name = "TestProject";
        MgLocation root = new MgLocation();
        MgType type = new MgAtom();
        MgStructure structure = new MgStructure();
        MgVariable structureVariable = new MgVariable();
        structureVariable.type = type;
        structure.variables.addLast(structureVariable);
        MgFunction function = new MgFunction();
        MgVariable functionVariable = new MgVariable();
        functionVariable.type = type;
        function.output = functionVariable;
        root.components.addLast(structure);
        root.components.addLast(function);
        root.components.addLast(type);
        project.root = root;
        Explorer explorer = new Explorer(() -> project);

        List<Node> results = new SearchService().findUsages(explorer, type);

        assertEquals(3, results.count());
        List<Object> parents = new List<>();
        for(Node result : results){
            assertSame(type, result.getObject());
            parents.addLast(result.getParentNode().getObject());
        }
        assertContains(parents, structureVariable);
        assertContains(parents, functionVariable);
        assertContains(parents, root.components);
    }

    @TestCase(order = 1)
    public void testCycleDetected(){
        MgProject project = new MgProject();
        MgLocation root = new MgLocation();
        MgLocation cycle = new MgLocation();
        cycle.components.addLast(root);
        root.components.addLast(cycle);
        project.root = root;
        Explorer explorer = new Explorer(() -> project);

        List<Node> results = new SearchService().findUsages(explorer, cycle);

        assertEquals(1, results.count());
    }
}
