package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.components.entity.EntityView;
import cz.mg.nativeapplication.gui.handlers.ActionUserEventHandler;
import cz.mg.nativeapplication.gui.utilities.GridSettingsFactory;

import javax.swing.*;
import java.awt.*;

import static cz.mg.nativeapplication.gui.utilities.NavigationCache.Node;


public class MainTabView extends JTabbedPane implements RefreshableView {
    private final @Mandatory @Link MainWindow mainWindow;

    public MainTabView(@Mandatory MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void open(@Optional Node node){
        if(node != null){
            Object object = node.getSelf();
            if(!hasObjectOpened(object)){
                if(object.getClass().isAnnotationPresent(Entity.class)){
                    addTab(node, new EntityView(mainWindow, object));
                }

                // todo - add support for more object types
            }
        }
    }

    public void closeActiveTab(){
        int index = getSelectedIndex();
        if(index != -1){
            remove(index);
        }
    }

    public void closeAllTabs(){
        while(getTabCount() > 0){
            remove(0);
        }
    }

    private void addTab(@Mandatory Node node, @Mandatory ObjectView view){
        addTab(null, null, view);
        setTabComponentAt(getTabCount() - 1, createTabHeader(node, view));
        setSelectedIndex(getTabCount() - 1);
    }

    private JPanel createTabHeader(@Mandatory Node node, @Mandatory Component component) {
        JPanel header = new JPanel();
        header.setLayout(new GridBagLayout());
        header.setOpaque(false);

        JLabel label = new JLabel();
        label.setText(node.getName());
        label.setIcon(node.getIcon());
        label.setOpaque(false);
        header.add(label, new GridSettingsFactory().create(0, 0, 0, 0, 0, 0, 0, 8));

        JButton closeButton = new JButton("x");
        closeButton.setBorder(null);
        closeButton.setOpaque(false);
        closeButton.setBackground(new Color(0, 0, 0, 0));
        closeButton.setForeground(new Color(180, 180, 180, 255));
        closeButton.addActionListener(new ActionUserEventHandler(mainWindow, () -> remove(component)));
        header.add(closeButton, new GridSettingsFactory().create(1, 0, 0, 0, 0, 0, 0, 0));

        return header;
    }

    private boolean hasObjectOpened(@Mandatory Object object){
        for(int i = 0; i < getTabCount(); i++){
            Component component = getComponentAt(i);
            if(component instanceof ObjectView){
                ObjectView objectView = (ObjectView) component;
                if(objectView.getObject() == object){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void refresh() {
        for(int i = 0; i < getTabCount(); i++){
            Component component = getComponentAt(i);
            if(component instanceof ObjectView){
                ObjectView objectView = (ObjectView) component;
                objectView.refresh();
            }
        }
    }
}
