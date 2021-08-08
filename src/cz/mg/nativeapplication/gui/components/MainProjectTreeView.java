package cz.mg.nativeapplication.gui.components;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.controls.UiTree;
import cz.mg.nativeapplication.gui.components.other.RefreshableView;
import cz.mg.nativeapplication.gui.handlers.KeyPressedUserEventHandler;
import cz.mg.nativeapplication.gui.handlers.MouseClickUserEventHandler;

import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static cz.mg.nativeapplication.gui.other.NavigationCache.Node;


public @Utility class MainProjectTreeView extends JScrollPane implements RefreshableView {
    private static final int PADDING = 4;

    private final @Mandatory @Link MainWindow mainWindow;
    private final @Mandatory @Link UiTree tree;

    public MainProjectTreeView(@Mandatory MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        tree = new UiTree();
        tree.setModel(new EntityTreeModel());
        tree.setCellRenderer(new EntityCellRenderer());
        tree.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        tree.setToggleClickCount(0);
        tree.addMouseListener(new MouseClickUserEventHandler(this::onMouseDoubleClick));
        tree.addKeyListener(new KeyPressedUserEventHandler(this::onKeyPressed));

        setViewportView(tree);
        ToolTipManager.sharedInstance().registerComponent(tree);
    }

    private void onMouseDoubleClick(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            if(e.getClickCount() == 2){
                if(tree.getRowForLocation(e.getX(), e.getY()) != -1){
                    openSelectedItem();
                    e.consume();
                }
            }
        }
    }

    private void onKeyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            openSelectedItem();
            e.consume();
        }
    }

    private void openSelectedItem(){
        if(tree.getLastSelectedPathComponent() != null){
            Node node = (Node) tree.getLastSelectedPathComponent();
            mainWindow.getMainView().getMainTabView().open(node);
        }
    }

    @Override
    public void refresh() {
        List<Object[]> expandedPaths = getExpandedPaths(tree);
        tree.setModel(new EntityTreeModel());
        setExpandedPaths(tree, restorePath(expandedPaths));
    }

    private @Mandatory List<Object[]> getExpandedPaths(@Mandatory UiTree tree){
        List<Object[]> expandedPaths = new List<>();
        for (int i = 0; i < tree.getRowCount() - 1; i++) {
            TreePath current = tree.getPathForRow(i);
            TreePath next = tree.getPathForRow(i + 1);
            if (current.isDescendant(next)) {
                expandedPaths.addLast(current.getPath());
            }
        }
        return expandedPaths;
    }

    private void setExpandedPaths(@Mandatory UiTree tree, @Mandatory List<Object[]> expandedPaths){
        for(Object[] expandedPath : expandedPaths){
            tree.expandPath(new TreePath(expandedPath));
        }
    }

    private @Mandatory List<Object[]> restorePath(@Mandatory List<Object[]> oldPaths) {
        List<Object[]> newPaths = new List<>();
        for(Object[] oldPath : oldPaths){
            Object[] newPath = restorePath(oldPath);
            if(newPath != null){
                newPaths.addLast(newPath);
            }
        }
        return newPaths;
    }

    private @Optional Object[] restorePath(@Mandatory Object[] oldPath){
        List<Object> newPath = new List<>();
        for(Object oldNodeObject : oldPath){
            Node oldNode = (Node) oldNodeObject;
            Node oldNodeParent = oldNode.getParent();
            Object oldParent = oldNodeParent != null ? oldNodeParent.getSelf() : null;
            Object entity = oldNode.getSelf();
            Node newNode = mainWindow.getNavigationCache().get(entity);
            if(newNode != null){
                Node newNodeParent = newNode.getParent();
                Object newParent = newNodeParent != null ? newNodeParent.getSelf() : null;
                if(oldParent == newParent){
                    newPath.addLast(newNode);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        return new Array(newPath).getJavaArray();
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
            UiLabel label = new UiLabel(node.getIcon(), node.getName());
            label.setToolTipText(node.getSelf().getClass().getSimpleName());

            if(selected){
                label.setOpaque(true);
                label.setBackground(UIManager.getDefaults().getColor("List.selectionBackground"));
            }

            return label;
        }
    }
}