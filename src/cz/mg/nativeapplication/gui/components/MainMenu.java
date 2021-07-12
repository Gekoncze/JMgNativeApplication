package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.handlers.ActionUserEventHandler;

import javax.swing.*;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.InputEvent.SHIFT_DOWN_MASK;


public @Utility class MainMenu extends JMenuBar {
    public MainMenu(@Mandatory MainWindow mainWindow) {
        add(createFileMenu(mainWindow));
        add(createEditMenu(mainWindow));
        add(createViewMenu(mainWindow));
    }

    private @Mandatory JMenu createFileMenu(@Mandatory MainWindow mainWindow){
        JMenuItem newProjectMenuItem = new JMenuItem("New project");
        newProjectMenuItem.setMnemonic('N');
        newProjectMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
        newProjectMenuItem.addActionListener(new ActionUserEventHandler(mainWindow, mainWindow::newProject));

        JMenuItem openProjectMenuItem = new JMenuItem("Open project");
        openProjectMenuItem.setMnemonic('O');
        openProjectMenuItem.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
        openProjectMenuItem.addActionListener(new ActionUserEventHandler(mainWindow, mainWindow::openProject));

        JMenuItem saveProjectMenuItem = new JMenuItem("Save project");
        saveProjectMenuItem.setMnemonic('S');
        saveProjectMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
        saveProjectMenuItem.addActionListener(new ActionUserEventHandler(mainWindow, mainWindow::saveProject));

        JMenuItem saveProjectAsMenuItem = new JMenuItem("Save project as");
        saveProjectAsMenuItem.setMnemonic('A');
        saveProjectAsMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK | SHIFT_DOWN_MASK));
        saveProjectAsMenuItem.addActionListener(new ActionUserEventHandler(mainWindow, mainWindow::saveProjectAs));

        JMenuItem closeProjectMenuItem = new JMenuItem("Close project");
        closeProjectMenuItem.setMnemonic('C');
        closeProjectMenuItem.addActionListener(new ActionUserEventHandler(mainWindow, mainWindow::closeProject));

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic('E');
        exitMenuItem.addActionListener(new ActionUserEventHandler(mainWindow, mainWindow::exit));

        JMenu menu = new JMenu("File");
        menu.setMnemonic('F');
        menu.add(newProjectMenuItem);
        menu.add(openProjectMenuItem);
        menu.add(saveProjectMenuItem);
        menu.add(saveProjectAsMenuItem);
        menu.add(closeProjectMenuItem);
        menu.add(exitMenuItem);
        return menu;
    }

    private @Mandatory JMenu createEditMenu(@Mandatory MainWindow mainWindow){
        JMenuItem undoMenuItem = new JMenuItem("Undo");
        undoMenuItem.setMnemonic('U');
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke('Z', CTRL_DOWN_MASK));
        undoMenuItem.addActionListener(new ActionUserEventHandler(mainWindow, () -> {
            if(mainWindow.getHistory() != null){
                mainWindow.getHistory().undo();
            }
        }));

        JMenuItem redoMenuItem = new JMenuItem("Redo");
        redoMenuItem.setMnemonic('E');
        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke('Z', CTRL_DOWN_MASK | SHIFT_DOWN_MASK));
        redoMenuItem.addActionListener(new ActionUserEventHandler(mainWindow, () -> {
            if(mainWindow.getHistory() != null){
                mainWindow.getHistory().redo();
            }
        }));

        JMenu menu = new JMenu("Edit");
        menu.setMnemonic('E');
        menu.add(undoMenuItem);
        menu.add(redoMenuItem);
        return menu;
    }

    private @Mandatory JMenu createViewMenu(@Mandatory MainWindow mainWindow){
        JMenuItem closeActiveTab = new JMenuItem("Close active tab");
        closeActiveTab.setAccelerator(KeyStroke.getKeyStroke('W', CTRL_DOWN_MASK));
        closeActiveTab.addActionListener(new ActionUserEventHandler(mainWindow,
            () -> mainWindow.getMainView().getMainTabView().closeActiveTab()
        ));

        JMenu menu = new JMenu("View");
        menu.setMnemonic('V');
        menu.add(closeActiveTab);
        return menu;
    }
}
