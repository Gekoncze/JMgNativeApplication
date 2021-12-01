package cz.mg.nativeapplication.mg.services.other;

import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.entities.components.MgStructure;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;

import java.lang.reflect.Field;


public class CollectionTypeProviderTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(CollectionTypeProviderTest.class);
    }

    @TestCase
    public void testReturnedTypesAreCorrect(){
        CollectionTypeProvider collectionTypeProvider = new CollectionTypeProvider();
        assertEquals(Integer.class, collectionTypeProvider.get(getTestClassField("integerList")));
        assertEquals(MgStructure.class, collectionTypeProvider.get(getTestClassField("structureList")));
        assertEquals(List.class, collectionTypeProvider.get(getTestClassField("listList")));
        assertEquals(Object.class, collectionTypeProvider.get(getTestClassField("objectList")));
        assertExceptionThrown(() -> collectionTypeProvider.get(getTestClassField("list")));
        assertExceptionThrown(() -> collectionTypeProvider.get(getTestClassField("object")));
        assertExceptionThrown(() -> collectionTypeProvider.get(getTestClassField("number")));
    }

    private Field getTestClassField(String name){
        try {
            return TestClass.class.getField(name);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private static class TestClass {
        public List<Integer> integerList = new List<>();
        public List<MgStructure> structureList = new List<>();
        public List<List> listList = new List<>();
        public List<Object> objectList = new List<>();
        public List list = new List<>();
        public Object object;
        public int number;
    }
}
