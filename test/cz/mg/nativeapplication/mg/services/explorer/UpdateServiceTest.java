package cz.mg.nativeapplication.mg.services.explorer;

import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.Initialization;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.entities.components.MgLocation;
import cz.mg.nativeapplication.mg.entities.components.MgVariable;
import cz.mg.nativeapplication.mg.services.history.TransactionManager;
import cz.mg.nativeapplication.mg.services.history.TransactionManagerProvider;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class UpdateServiceTest implements Test {
    public static void main(String[] args) {
        new Initialization().init();
        new SingleTestClassRunner().run(UpdateServiceTest.class);
    }

    @TestCase(order = 1)
    public void testUpdateEntity(){
        MgProject project = new MgProject();
        MgLocation root = new MgLocation();
        int indexOfRootField = MgProject.entity.getFields().indexOf(MgProject.entity.getField("root"));

        new TransactionManagerProvider().set(new TransactionManager());
        new TransactionManagerProvider().get().beginTransaction();
        new UpdateService().update(project, indexOfRootField, root);

        assertSame(root, project.root);
    }

    @TestCase(order = 2)
    public void testUpdateList(){
        List<MgVariable> variables = new List<>();
        MgVariable variable = new MgVariable();
        variables.addLast(null);

        new TransactionManagerProvider().set(new TransactionManager());
        new TransactionManagerProvider().get().beginTransaction();
        new UpdateService().update(variables, 0, variable);

        assertSame(variable, variables.getFirst());
    }

    @TestCase(order = 3)
    public void testIllegalUpdate(){
        assertExceptionThrown(UnsupportedOperationException.class, () -> {
            new TransactionManagerProvider().set(new TransactionManager());
            new TransactionManagerProvider().get().beginTransaction();
            new UpdateService().update("test", 0, true);
        });
    }
}
