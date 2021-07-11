package cz.mg.nativeapplication.gui.utilities;

import cz.mg.annotations.classes.Service;

import java.awt.*;


public @Service class GridBagConstraintFactory {
    public GridBagConstraints create(int x, int y, int wx, int wy, int pTop, int pLeft, int pBottom, int pRight){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.weightx = wx;
        constraints.weighty = wy;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(pTop, pLeft, pBottom, pRight);
        return constraints;
    }
}
