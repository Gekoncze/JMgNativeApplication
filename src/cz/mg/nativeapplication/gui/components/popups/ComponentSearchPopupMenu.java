package cz.mg.nativeapplication.gui.components.popups;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.ToStringBuilder;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.components.controls.UiDummyMenuItem;
import cz.mg.nativeapplication.gui.components.controls.UiMenuItem;
import cz.mg.nativeapplication.gui.components.controls.UiPopupMenu;
import cz.mg.nativeapplication.gui.handlers.ActionUserEventHandler;
import cz.mg.nativeapplication.gui.other.Navigation;
import cz.mg.nativeapplication.gui.other.NavigationNode;
import cz.mg.nativeapplication.gui.services.ComponentSearch;
import cz.mg.nativeapplication.gui.services.ObjectIconProvider;
import cz.mg.nativeapplication.mg.entities.components.MgComponent;

import java.awt.*;


public @Utility class ComponentSearchPopupMenu extends UiPopupMenu {
    private final @Mandatory @Part ActionUserEventHandler.Handler actionEventHandler;
    private @Optional @Link MgComponent selectedComponent;

    public ComponentSearchPopupMenu(@Mandatory ActionUserEventHandler.Handler actionEventHandler) {
        this.actionEventHandler = actionEventHandler;
    }

    public @Optional MgComponent getSelectedComponent() {
        return selectedComponent;
    }

    public void search(
        @Mandatory Component component,
        @Mandatory Class classFilter,
        @Mandatory String nameFilter,
        @Mandatory Navigation navigation
    ) {
        removeAll();

        List<MgComponent> results = new ComponentSearch().search(navigation, classFilter, nameFilter);

        for(MgComponent result : results){
            add(
                new UiMenuItem(
                    new ObjectIconProvider().get(result),
                    findComponentPath(navigation, result),
                    () -> {
                        selectedComponent = result;
                        actionEventHandler.run();
                    }
                )
            );
        }

        if(results.count() < 1){
            add(new UiDummyMenuItem("No results."));
        }

        show(component, 0, component.getHeight());
    }

    private @Mandatory String findComponentPath(
        @Mandatory Navigation navigation,
        @Mandatory MgComponent component
    ){
        List<MgComponent> path = new List<>();
        NavigationNode current = navigation.get(component);
        while(current != null){
            if(current.getSelf() instanceof MgComponent){
                path.addFirst((MgComponent) current.getSelf());
            }
            current = current.getParent();
        }
        path.removeFirst(); // remove root location
        return new ToStringBuilder<>(path).convert(c -> c.name).delim(".").build();
    }
}
