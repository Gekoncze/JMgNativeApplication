package cz.mg.entity.explorer;

import all.Preparation;
import cz.mg.collections.list.List;
import cz.mg.entity.explorer.services.UpdateService;
import cz.mg.nativeapplication.gui.Initialization;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.entities.components.MgAtom;
import cz.mg.nativeapplication.mg.entities.components.MgLocation;
import cz.mg.nativeapplication.mg.entities.components.MgVariable;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class UpdateServiceTest implements Test {
    public static void main(String[] args) {
        new Preparation().prepare();
        new SingleTestClassRunner().run(UpdateServiceTest.class);
    }

    @TestCase(order = 1)
    public void testUpdateEntity(){
        MgProject project = new MgProject();
        MgLocation root = new MgLocation();
        int indexOfRootField = MgProject.entity.getFields().indexOf(MgProject.entity.getField("root"));

        Explorer explorer = new Explorer(new Initialization().createMapper());
        explorer.setProject(project);
        explorer.getTransactionManager().transaction(() -> {
            new UpdateService().update(explorer, project, indexOfRootField, root);
        });

        assertSame(root, project.root);
    }

    @TestCase(order = 2)
    public void testUpdateList(){
        List<MgVariable> variables = new List<>();
        MgVariable variable = new MgVariable();
        variables.addLast(null);

        Explorer explorer = new Explorer(new Initialization().createMapper());
        explorer.setProject(variables);
        explorer.getTransactionManager().transaction(() -> {
            new UpdateService().update(explorer, variables, 0, variable);
        });

        assertSame(variable, variables.getFirst());
    }

    @TestCase(order = 3)
    public void testIllegalUpdate(){
        assertExceptionThrown(() -> {
            String test = "test";
            Explorer explorer = new Explorer(new Initialization().createMapper());
            explorer.setProject(test);
            explorer.getTransactionManager().transaction(() -> {
                new UpdateService().update(explorer, "test", 0, true);
            });
        });
    }

    @TestCase(order = 4)
    public void testUpdateEntityOutOfBounds(){
        assertExceptionThrown(ArrayIndexOutOfBoundsException.class, () -> {
            MgAtom atom = new MgAtom();
            atom.name = "test";
            Explorer explorer = new Explorer(new Initialization().createMapper());
            explorer.setProject(atom);
            new UpdateService().update(explorer, atom, -1, null);
        });

        assertExceptionThrown(ArrayIndexOutOfBoundsException.class, () -> {
            MgAtom atom = new MgAtom();
            atom.name = "test";
            Explorer explorer = new Explorer(new Initialization().createMapper());
            explorer.setProject(atom);
            new UpdateService().update(explorer, atom, 1, null);
        });
    }

    @TestCase(order = 5)
    public void testUpdateListOutOfBounds(){
        assertExceptionThrown(ArrayIndexOutOfBoundsException.class, () -> {
            List list = new List();
            list.addLast("test");
            Explorer explorer = new Explorer(new Initialization().createMapper());
            explorer.setProject(list);
            new UpdateService().update(explorer, list, -1, null);
        });

        assertExceptionThrown(ArrayIndexOutOfBoundsException.class, () -> {
            List list = new List();
            list.addLast("test");
            Explorer explorer = new Explorer(new Initialization().createMapper());
            explorer.setProject(list);
            new UpdateService().update(explorer, list, 1, null);
        });
    }
}
