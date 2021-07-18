package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.components.RefreshableComponent;
import cz.mg.nativeapplication.gui.utilities.GridSettingsFactory;
import cz.mg.nativeapplication.sevices.EntityClass;
import cz.mg.nativeapplication.sevices.EntityClassCache;
import cz.mg.nativeapplication.sevices.EntityField;

import javax.swing.*;
import java.awt.*;


public @Utility class EntityView extends JScrollPane implements RefreshableComponent {
    private static final int PADDING = 2;

    private final @Mandatory List<RefreshableComponent> fields = new List<>();

    public EntityView(@Mandatory MainWindow mainWindow, @Mandatory Object entity) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        EntityClass entityClass = EntityClassCache.getInstance().get(entity.getClass());
        int y = 0;
        for(EntityField entityField : entityClass.getFields()){
            if(entityField.isAnnotationPresent(Link.class)){
                if(!Iterable.class.isAssignableFrom(entityField.getField().getType())){
                    EntityFieldLinkSelect field = new EntityFieldLinkSelect(mainWindow, entity, entityField);
                    panel.add(field.getLabel(), new GridSettingsFactory().create(0, y, 0, 0, PADDING));
                    panel.add(field.getTextField(), new GridSettingsFactory().create(1, y, 1, 0, PADDING));
                    panel.add(field.getClearButton(), new GridSettingsFactory().create(2, y, 0, 0, PADDING));
                    fields.addLast(field);
                    y++;
                }

                // todo - handle other field types
            }

            // todo - handle other field types
        }

        // adding dummy panel to align components on top
        panel.add(new JPanel(), new GridSettingsFactory().create(0, y, 0, 1, 0));

        setViewportView(panel);
    }

    @Override
    public void refresh() {
        for(RefreshableComponent field : fields){
            field.refresh();
        }
    }
}
