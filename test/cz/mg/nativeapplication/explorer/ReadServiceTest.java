package cz.mg.nativeapplication.explorer;

import all.Preparation;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.explorer.services.ReadService;
import cz.mg.nativeapplication.mg.entities.components.MgAtom;
import cz.mg.nativeapplication.mg.entities.components.MgType;
import cz.mg.nativeapplication.mg.entities.components.MgVariable;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class ReadServiceTest implements Test {
    public static void main(String[] args) {
        new Preparation().prepare();
        new SingleTestClassRunner().run(ReadServiceTest.class);
    }

    @TestCase(order = 1)
    public void testReadFromEntity(){
        MgType type = new MgAtom();
        MgVariable variable = new MgVariable();
        variable.name = null;
        variable.pointers = 1;
        variable.type = type;

        ReadService readService = new ReadService();

        // field order is alphabetical
        assertExceptionThrown(ArrayIndexOutOfBoundsException.class, () -> readService.read(variable, -1));
        assertNull(readService.read(variable, 0));
        assertSame(variable.pointers, readService.read(variable, 1));
        assertEquals(variable.type, readService.read(variable, 2));
        assertExceptionThrown(ArrayIndexOutOfBoundsException.class, () -> readService.read(variable, 3));
    }

    @TestCase(order = 2)
    public void testReadFromList(){
        List<MgAtom> list = new List<>();
        MgAtom first = new MgAtom();
        MgAtom second = new MgAtom();
        list.addLast(first);
        list.addLast(second);

        ReadService readService = new ReadService();

        assertExceptionThrown(ArrayIndexOutOfBoundsException.class, () -> readService.read(list, -1));
        assertSame(first, readService.read(list, 0));
        assertSame(second, readService.read(list, 1));
        assertExceptionThrown(ArrayIndexOutOfBoundsException.class, () -> readService.read(list, 2));
    }
}
