package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.nativeapplication.explorer.Explorer;
import cz.mg.nativeapplication.gui.components.views.EntityView;
import cz.mg.nativeapplication.gui.components.views.ObjectView;
import cz.mg.nativeapplication.gui.components.views.ScrollingObjectView;


public @Service class ObjectViewFactory {
    public @Mandatory ObjectView create(@Mandatory Explorer explorer, @Mandatory Object object){
        if(object.getClass().isAnnotationPresent(Entity.class)){
            return new ScrollingObjectView(new EntityView(explorer, object));
        }

        throw new UnsupportedOperationException("Unsupported object of type '" + object.getClass().getSimpleName() + "'."); // TODO
    }
}
