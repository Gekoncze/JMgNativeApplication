package cz.mg.nativeapplication;

import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.mg.components.MgStructure;
import cz.mg.nativeapplication.sevices.gui.CollectionTypeProvider;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;

import java.lang.reflect.Field;


public class CollectionTypeProviderTest implements Test {
    public static void main(String[] args) {
        new SingleTestRunner().run(new CollectionTypeProviderTest());
    }

    @TestCase
    public void testReturnedTypesAreCorrect(){
        CollectionTypeProvider collectionTypeProvider = new CollectionTypeProvider();
        assertEquals(Integer.class, collectionTypeProvider.get(getTestClassField("integerList")));
        assertEquals(MgStructure.class, collectionTypeProvider.get(getTestClassField("structureList")));
        assertEquals(List.class, collectionTypeProvider.get(getTestClassField("listList")));
        assertEquals(Object.class, collectionTypeProvider.get(getTestClassField("objectList")));
        assertEquals(null, collectionTypeProvider.get(getTestClassField("list")));
        assertEquals(null, collectionTypeProvider.get(getTestClassField("object")));
        assertEquals(null, collectionTypeProvider.get(getTestClassField("number")));
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
