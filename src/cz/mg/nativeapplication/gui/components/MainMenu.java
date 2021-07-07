package cz.mg.nativeapplication.gui.components;

import cz.mg.nativeapplication.gui.MainWindow;

import javax.swing.*;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.InputEvent.SHIFT_DOWN_MASK;


public class MainMenu extends JMenuBar {
    public MainMenu(MainWindow mainWindow) {
        JMenuItem newProjectMenuItem = new JMenuItem("New project");
        newProjectMenuItem.setMnemonic('N');
        newProjectMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
        newProjectMenuItem.addActionListener(e -> mainWindow.newProject());

        JMenuItem openProjectMenuItem = new JMenuItem("Open project");
        openProjectMenuItem.setMnemonic('O');
        openProjectMenuItem.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
        openProjectMenuItem.addActionListener(e -> mainWindow.openProject());

        JMenuItem saveProjectMenuItem = new JMenuItem("Save project");
        saveProjectMenuItem.setMnemonic('S');
        saveProjectMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
        saveProjectMenuItem.addActionListener(e -> mainWindow.saveProject());

        JMenuItem saveProjectAsMenuItem = new JMenuItem("Save project as");
        saveProjectAsMenuItem.setMnemonic('A');
        saveProjectAsMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK | SHIFT_DOWN_MASK));
        saveProjectAsMenuItem.addActionListener(e -> mainWindow.saveProjectAs());

        JMenuItem closeProjectMenuItem = new JMenuItem("Close project");
        closeProjectMenuItem.setMnemonic('C');
        closeProjectMenuItem.addActionListener(e -> mainWindow.closeProject());

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic('E');
        exitMenuItem.addActionListener(e -> mainWindow.exit());

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        fileMenu.add(newProjectMenuItem);
        fileMenu.add(openProjectMenuItem);
        fileMenu.add(saveProjectMenuItem);
        fileMenu.add(saveProjectAsMenuItem);
        fileMenu.add(closeProjectMenuItem);
        fileMenu.add(exitMenuItem);

        add(fileMenu);
    }
}
