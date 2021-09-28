package cz.mg.nativeapplication.gui.components.entity.multi.link;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Shared;
import cz.mg.entity.EntityField;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.nativeapplication.gui.components.controls.UiLabel;
import cz.mg.nativeapplication.gui.components.entity.multi.EntityMultiSelect;


public @Utility class EntityLinkMultiSelect extends EntityMultiSelect {
    private final @Mandatory @Shared UiLabel label;


    public EntityLinkMultiSelect(
        @Mandatory MainWindow mainWindow,
        @Mandatory Object entity,
        @Mandatory EntityField entityField
    ) {
        super(mainWindow, entity, entityField);
        this.label = new UiLabel(entityField.getName());

        // TODO
    }

    @Override
    public @Mandatory UiLabel getLabel() {
        return label;
    }

    @Override
    protected @Mandatory Object createValue() {
        // TODO
        return null;
    }

    @Override
    protected void editValue(@Mandatory Object object) {
        // TODO
    }

    @Override
    public void refresh() {
        // TODO
    }
}
