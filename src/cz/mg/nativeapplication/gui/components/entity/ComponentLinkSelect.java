//package cz.mg.nativeapplication.gui.components.entity;
//
//import cz.mg.annotations.classes.Utility;
//import cz.mg.annotations.requirement.Mandatory;
//import cz.mg.annotations.requirement.Optional;
//import cz.mg.nativeapplication.entities.mg.components.MgComponent;
//import cz.mg.nativeapplication.gui.MainWindow;
//
//import javax.swing.*;
//import java.awt.event.FocusAdapter;
//import java.awt.event.FocusEvent;
//
//
//public @Utility class ComponentLinkSelect extends JTextField {
//
//    private final @Mandatory MainWindow mainWindow;
//    private final @Mandatory Class typeFilter;
//    private @Optional MgComponent selectedComponent;
//
//    public ComponentLinkSelect(@Mandatory MainWindow mainWindow, @Mandatory Class typeFilter) {
//        this.mainWindow = mainWindow;
//        this.typeFilter = typeFilter;
//        addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusLost(FocusEvent e) {
//                setText(getComponentName(selectedComponent));
//            }
//        });
//    }
//
//    private static @Mandatory String getComponentName(@Optional MgComponent component){
//        if(component != null){
//            if(component.name != null){
//                if(component.name.trim().length() > 0){
//                    return component.name;
//                } else {
//                    return "?";
//                }
//            } else {
//                return "?";
//            }
//        } else {
//            return "";
//        }
//    }
//}
