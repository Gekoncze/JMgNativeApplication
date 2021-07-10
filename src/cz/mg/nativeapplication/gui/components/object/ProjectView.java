package cz.mg.nativeapplication.gui.components.object;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.entities.mg.MgProject;
import cz.mg.nativeapplication.entities.mg.components.MgFunction;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.components.part.ComponentLinkSelect;
import cz.mg.nativeapplication.gui.handlers.ActionUserEventHandler;

import javax.swing.*;
import java.awt.*;


public @Utility class ProjectView extends JScrollPane {
    private final @Mandatory @Link MgProject project;

    public ProjectView(@Mandatory MainWindow mainWindow, @Mandatory MgProject project) {
        this.project = project;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel mainFunctionLabel = new JLabel("Main function");

        GridBagConstraints mainFunctionLabelSettings = new GridBagConstraints();
        mainFunctionLabelSettings.gridx = 0;
        mainFunctionLabelSettings.gridy = 0;
        mainFunctionLabelSettings.fill = GridBagConstraints.NONE;
        mainFunctionLabelSettings.weightx = 0;
        mainFunctionLabelSettings.weightx = 0;

        panel.add(mainFunctionLabel, mainFunctionLabelSettings);

        ComponentLinkSelect mainFunctionSelect = new ComponentLinkSelect(mainWindow, MgFunction.class);
        mainFunctionSelect.setPreferredSize(new Dimension(120, (int) mainFunctionSelect.getPreferredSize().getHeight()));
        mainFunctionSelect.addActionListener(new ActionUserEventHandler(mainWindow,
            () -> project.main = (MgFunction) mainFunctionSelect.getSelectedComponent()
        ));

        GridBagConstraints mainFunctionSelectSettings = new GridBagConstraints();
        mainFunctionSelectSettings.gridx = 1;
        mainFunctionSelectSettings.gridy = 0;
        mainFunctionSelectSettings.fill = GridBagConstraints.NONE;
        mainFunctionSelectSettings.weightx = 0;
        mainFunctionSelectSettings.weightx = 0;

        panel.add(mainFunctionSelect, mainFunctionSelectSettings);

        setViewportView(panel);
    }
}
