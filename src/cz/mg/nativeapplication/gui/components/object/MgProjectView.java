package cz.mg.nativeapplication.gui.components.object;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.entities.mg.MgProject;
import cz.mg.nativeapplication.entities.mg.components.MgFunction;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.components.RefreshableComponent;
import cz.mg.nativeapplication.gui.components.part.ComponentLinkSelect;
import cz.mg.nativeapplication.gui.handlers.ChangeUserEventHandler;
import cz.mg.nativeapplication.gui.utilities.GridSettingsFactory;

import javax.swing.*;
import java.awt.*;


public @Utility class MgProjectView extends JScrollPane implements RefreshableComponent {
    private static final int PADDING = 2;

    private final @Mandatory @Link MgProject project;
    private final @Mandatory @Part ComponentLinkSelect mainFunctionSelect;

    public MgProjectView(@Mandatory MainWindow mainWindow, @Mandatory MgProject project) {
        this.project = project;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        mainFunctionSelect = new ComponentLinkSelect(mainWindow, MgFunction.class, "Main function", project.main);
        mainFunctionSelect.setChangeHandler(new ChangeUserEventHandler(mainWindow, () -> {
            // todo - change to history action
            project.main = (MgFunction) mainFunctionSelect.getValue();
        }));

        panel.add(mainFunctionSelect.getLabel(), new GridSettingsFactory().create(0, 0, 0, 0, PADDING));
        panel.add(mainFunctionSelect.getTextField(), new GridSettingsFactory().create(1, 0, 1, 0, PADDING));
        panel.add(mainFunctionSelect.getClearButton(), new GridSettingsFactory().create(2, 0, 0, 0, PADDING));

        setViewportView(panel);
    }

    @Override
    public void refresh() {
        // todo - cannot use set value because that might update history
        // mainFunctionSelect.setValue(project.main);
    }
}
