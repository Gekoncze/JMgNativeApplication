package cz.mg.nativeapplication.gui.components.popups;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.ui.controls.UiPopupMenu;
import cz.mg.nativeapplication.gui.ui.controls.menu.UiValueMenuItem;

import static cz.mg.nativeapplication.gui.ui.controls.menu.UiValueMenuItem.SelectEventHandler;


public @Utility class EnumPopupMenu<T extends Enum> extends UiPopupMenu {
    public EnumPopupMenu(@Mandatory Class<T> clazz, @Mandatory SelectEventHandler<T> handler) {
        super(createItems(clazz, handler));
    }

    private static <T extends Enum> @Mandatory List<UiValueMenuItem> createItems(
        @Mandatory Class<T> clazz,
        @Mandatory SelectEventHandler handler
    ){
        List<UiValueMenuItem> items = new List<>();
        for(Enum value : clazz.getEnumConstants()){
            items.addLast(new UiValueMenuItem(value, value.name(), handler));
        }
        return items;
    }
}
