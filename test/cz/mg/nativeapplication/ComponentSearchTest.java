package cz.mg.nativeapplication;

import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.entities.MgProject;
import cz.mg.nativeapplication.mg.entities.components.MgComponent;
import cz.mg.nativeapplication.mg.entities.components.MgFunction;
import cz.mg.nativeapplication.mg.entities.components.MgLocation;
import cz.mg.nativeapplication.mg.entities.components.MgStructure;
import cz.mg.nativeapplication.gui.other.Navigation;
import cz.mg.nativeapplication.gui.services.ComponentSearch;
import cz.mg.nativeapplication.gui.services.NavigationCreator;
import cz.mg.nativeapplication.mg.services.creator.MgProjectCreator;


public class ComponentSearchTest {
    public static void main(String[] args) {
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

        ComponentSearch componentSearch = new ComponentSearch();
        List<MgComponent> results = componentSearch.search(navigation, MgStructure.class, "FooBar");

        System.out.println("Found " + results.count() + " results:");
        for(MgComponent result : results){
            System.out.println(result.name);
        }
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

    private static MgFunction createFunction(String name){
        MgFunction function = new MgFunction();
        function.name = name;
        return function;
    }
}
