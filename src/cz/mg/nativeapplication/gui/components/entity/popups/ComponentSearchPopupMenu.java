package cz.mg.nativeapplication.gui.components.entity.popups;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.ToStringBuilder;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.ui.controls.UiPopupMenu;
import cz.mg.nativeapplication.gui.ui.controls.menu.UiDummyMenuItem;
import cz.mg.nativeapplication.gui.ui.controls.menu.UiValueMenuItem;
import cz.mg.nativeapplication.gui.services.ObjectImageProvider;
import cz.mg.nativeapplication.gui.utilities.Navigation;
import cz.mg.nativeapplication.gui.utilities.NavigationNode;
import cz.mg.nativeapplication.gui.services.ComponentSearch;
import cz.mg.nativeapplication.mg.entities.components.MgComponent;

import java.awt.*;

import static cz.mg.nativeapplication.gui.ui.controls.menu.UiValueMenuItem.SelectEventHandler;


public @Utility class ComponentSearchPopupMenu extends UiPopupMenu {
    private final @Mandatory @Part SelectEventHandler<MgComponent> selectEventHandler;

    public ComponentSearchPopupMenu(@Mandatory SelectEventHandler<MgComponent> selectEventHandler) {
        this.selectEventHandler = selectEventHandler;
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
                new UiValueMenuItem<>(
                    new ObjectImageProvider().get(result),
                    result,
                    findComponentPath(navigation, result),
                    selectEventHandler
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
