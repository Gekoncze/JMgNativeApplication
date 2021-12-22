package cz.mg.nativeapplication.gui.services;

import cz.mg.collections.list.List;
import cz.mg.entity.explorer.gui.services.EntitySearch;
import cz.mg.entity.explorer.gui.services.NavigationCreator;
import cz.mg.nativeapplication.gui.Initialization;
import cz.mg.entity.explorer.gui.utilities.Navigation;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.entities.components.MgLocation;
import cz.mg.nativeapplication.mg.entities.components.MgStructure;
import cz.mg.nativeapplication.mg.services.creator.MgProjectCreator;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;


public class ComponentSearchTest implements Test {
    public static void main(String[] args) {
        new Initialization().init();
        new SingleTestClassRunner().run(ComponentSearchTest.class);
    }

    @TestCase
    public void test(){
        MgProject project = new MgProjectCreator().create("TestProject");

        MgLocation foo = createLocation("foo");
        project.root.components.addLast(foo);

        MgLocation bar = createLocation("bar");
        foo.components.addLast(bar);

        bar.components.addLast(createStructure("FooFoo"));
        bar.components.addLast(createStructure("BarBar"));
        bar.components.addLast(createStructure("FooBarNew"));
        bar.components.addLast(createStructure("FooBar"));
        bar.components.addLast(createStructure("FooBrr"));
        bar.components.addLast(createStructure("Abc"));
        bar.components.addLast(createStructure("AbFooBar"));

        Navigation navigation = new NavigationCreator().create(project);

        EntitySearch entitySearch = new EntitySearch();
        List<Object> results = entitySearch.search(navigation, MgStructure.class, "FooBar");

        assertEquals(3, results.count());
        assertContains(results, bar.components.get(2));
        assertContains(results, bar.components.get(3));
        assertContains(results, bar.components.get(6));
    }

    private static MgLocation createLocation(String name){
        MgLocation location = new MgLocation();
        location.name = name;
        return location;
    }

    private static MgStructure createStructure(String name){
        MgStructure structure = new MgStructure();
        structure.name = name;
        return structure;
    }
}
