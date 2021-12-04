package cz.mg.nativeapplication.explorer;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.EntityClass;
import cz.mg.entity.EntityClassFactory;
import cz.mg.nativeapplication.explorer.services.ReadService;
import cz.mg.nativeapplication.gui.Initialization;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class ReadServiceTest implements Test {
    public static void main(String[] args) {
        new Initialization().init();
        new SingleTestClassRunner().run(ReadServiceTest.class);
    }

    @TestCase
    public void testReadFromEntity(){
        TestEntity nextEntity = new TestEntity();
        TestEntity testEntity = new TestEntity();
        testEntity.value = 1;
        testEntity.next = nextEntity;

        TestEntity.entity = new EntityClassFactory().create(TestEntity.class);
        ReadService readService = new ReadService();

        // note: field order is alphabetical
        assertSame(testEntity.next, readService.read(testEntity, 0));
        assertEquals(testEntity.value, readService.read(testEntity, 1));
        assertNull(readService.read(nextEntity, 0));
        assertExceptionThrown(ArrayIndexOutOfBoundsException.class, () -> readService.read(testEntity, -1));
        assertExceptionThrown(ArrayIndexOutOfBoundsException.class, () -> readService.read(testEntity, 2));
    }

    @TestCase
    public void testReadFromList(){
        List<TestEntity> list = new List<>();
        TestEntity first = new TestEntity();
        TestEntity second = new TestEntity();
        list.addLast(first);
        list.addLast(second);

        ReadService readService = new ReadService();

        assertSame(first, readService.read(list, 0));
        assertSame(second, readService.read(list, 1));
        assertExceptionThrown(ArrayIndexOutOfBoundsException.class, () -> readService.read(list, -1));
        assertExceptionThrown(ArrayIndexOutOfBoundsException.class, () -> readService.read(list, 2));
    }

    public static @Entity class TestEntity {
        public static EntityClass entity;

        public @Value int value;
        public @Part TestEntity next;

        public TestEntity() {
        }
    }
}
