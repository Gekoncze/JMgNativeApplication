package cz.mg.nativeapplication;

import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.mg.MgProject;
import cz.mg.nativeapplication.entities.mg.components.MgComponent;
import cz.mg.nativeapplication.entities.mg.components.MgFunction;
import cz.mg.nativeapplication.entities.mg.components.MgLocation;
import cz.mg.nativeapplication.entities.mg.components.MgStructure;
import cz.mg.nativeapplication.gui.utilities.NavigationCache;
import cz.mg.nativeapplication.sevices.gui.ComponentSearch;
import cz.mg.nativeapplication.sevices.gui.NavigationCacheCreator;
import cz.mg.nativeapplication.sevices.mg.creator.MgProjectCreator;


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

        NavigationCache navigationCache = new NavigationCacheCreator().create(project);

        ComponentSearch componentSearch = new ComponentSearch();
        List<MgComponent> results = componentSearch.search(navigationCache, MgStructure.class, "FooBar");

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
