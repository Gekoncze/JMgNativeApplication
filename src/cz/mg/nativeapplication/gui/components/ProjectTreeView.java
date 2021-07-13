package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.handlers.MouseDoubleClickUserEventHandler;

import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;

import static cz.mg.nativeapplication.gui.utilities.NavigationCache.Node;


public @Utility class ProjectTreeView extends JScrollPane implements RefreshableComponent {
    private static final int PADDING = 4;

    private final @Mandatory @Link MainWindow mainWindow;
    private final @Mandatory @Link JTree tree;

    public ProjectTreeView(@Mandatory MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        tree = new JTree();
        tree.setModel(new EntityTreeModel());
        tree.setCellRenderer(new EntityCellRenderer());
        tree.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        tree.setToggleClickCount(0);
        tree.addMouseListener(new MouseDoubleClickUserEventHandler(mainWindow, this::onMouseDoubleClick));

        setViewportView(tree);
        ToolTipManager.sharedInstance().registerComponent(tree);
    }

    private void onMouseDoubleClick(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            if(e.getClickCount() == 2){
                if(tree.getLastSelectedPathComponent() != null){
                    if(tree.getRowForLocation(e.getX(), e.getY()) != -1){
                        openSelectedItem();
                    }
                }
                e.consume();
            }
        }
    }

    private void openSelectedItem(){
        Node node = (Node) tree.getLastSelectedPathComponent();
        mainWindow.getMainView().getMainTabView().open(node);
    }

    @Override
    public void refresh() {
        tree.setModel(new EntityTreeModel());
    }

    private class EntityTreeModel implements TreeModel {
        @Override
        public Object getRoot() {
            return mainWindow.getNavigationCache().getRoot();
        }

        @Override
        public Object getChild(Object o, int i) {
            if(o == null) return null;
            Node self = (Node) o;
            return self.getChildren().get(i);
        }

        @Override
        public int getChildCount(Object o) {
            if(o == null) return 0;
            Node self = (Node) o;
            return self.getChildren().count();
        }

        @Override
        public boolean isLeaf(Object o) {
            return getChildCount(o) < 1;
        }

        @Override
        public void valueForPathChanged(TreePath treePath, Object o) {
            throw new UnsupportedOperationException("Readonly model.");
        }

        @Override
        public int getIndexOfChild(Object parent, Object child) {
            if(parent == null || child == null) return -1;
            Node parentNode = (Node) parent;
            Node childNode = (Node) child;
            int i = 0;
            for(Node currentNode : parentNode.getChildren()){
                if(currentNode == childNode){
                    return i;
                }
                i++;
            }
            return -1;
        }

        @Override
        public void addTreeModelListener(TreeModelListener treeModelListener) {
        }

        @Override
        public void removeTreeModelListener(TreeModelListener treeModelListener) {
        }
    }

    private static class EntityCellRenderer implements TreeCellRenderer {
        @Override
        public java.awt.Component getTreeCellRendererComponent(
            JTree tree, Object o,
            boolean selected, boolean expanded, boolean leaf,
            int row, boolean hasFocus
        ) {
            Node node = (Node) o;
            JLabel label = new JLabel();
            label.setText(node.getName());
            label.setIcon(node.getIcon());
            label.setToolTipText(node.getSelf().getClass().getSimpleName());

            if(selected){
                label.setOpaque(true);
                label.setBackground(UIManager.getDefaults().getColor("List.selectionBackground"));
            }

            return label;
        }
    }
}
