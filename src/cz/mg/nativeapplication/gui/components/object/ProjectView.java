package cz.mg.nativeapplication.gui.components.object;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.entities.mg.MgProject;
import cz.mg.nativeapplication.entities.mg.components.MgFunction;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.components.part.ComponentLinkSelect;
import cz.mg.nativeapplication.gui.handlers.ChangeUserEventHandler;
import cz.mg.nativeapplication.gui.utilities.GridBagConstraintFactory;

import javax.swing.*;
import java.awt.*;


public @Utility class ProjectView extends JScrollPane {
    private static final int PADDING = 4;

    private final @Mandatory @Link MgProject project;
    private final @Mandatory @Part ComponentLinkSelect mainFunctionSelect;

    public ProjectView(@Mandatory MainWindow mainWindow, @Mandatory MgProject project) {
        this.project = project;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        mainFunctionSelect = new ComponentLinkSelect(mainWindow, MgFunction.class, "Main function");
        mainFunctionSelect.setChangeHandler(new ChangeUserEventHandler(mainWindow, () -> {
            project.main = (MgFunction) mainFunctionSelect.getSelectedComponent();
        }));

        panel.add(mainFunctionSelect.getLabel(), new GridBagConstraintFactory().create(
            0, 0, 0, 0, PADDING, PADDING, PADDING, PADDING
        ));

        panel.add(mainFunctionSelect.getTextField(), new GridBagConstraintFactory().create(
            1, 0, 1, 0, PADDING, 0, PADDING, PADDING
        ));

        setViewportView(panel);
    }
}
