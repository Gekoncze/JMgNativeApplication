package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.entities.mg.MgProject;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.components.object.ProjectView;
import cz.mg.nativeapplication.gui.handlers.ActionUserEventHandler;
import cz.mg.nativeapplication.gui.utilities.GridSettingsFactory;

import javax.swing.*;
import java.awt.*;

import static cz.mg.nativeapplication.gui.utilities.NavigationCache.Node;


public class MainTabView extends JTabbedPane {
    private final @Mandatory @Link MainWindow mainWindow;

    public MainTabView(@Mandatory MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void open(@Optional Node node){
        if(node != null){
            if(node.getSelf() instanceof MgProject){
                addTab(node, new ProjectView(mainWindow, (MgProject) node.getSelf()));
            }

            // todo - add support for more object types
        }
    }

    public void closeActiveTab(){
        int index = getSelectedIndex();
        if(index != -1){
            remove(index);
        }
    }

    private void addTab(@Mandatory Node node, @Mandatory Component component){
        addTab(null, null, component);
        setTabComponentAt(getTabCount() - 1, createTabHeader(node, component));
    }

    private JPanel createTabHeader(@Mandatory Node node, @Mandatory Component component){
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
}
