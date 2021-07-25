package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.components.ObjectView;
import cz.mg.nativeapplication.gui.components.RefreshableView;
import cz.mg.nativeapplication.gui.utilities.GridSettingsFactory;
import cz.mg.nativeapplication.sevices.EntityClass;
import cz.mg.nativeapplication.sevices.EntityClassCache;
import cz.mg.nativeapplication.sevices.EntityField;

import javax.swing.*;
import java.awt.*;


public @Utility class EntityView extends ObjectView {
    private static final int PADDING = 2;

    private final @Mandatory @Link Object entity;
    private final @Mandatory @Part List<RefreshableView> fields = new List<>();

    public EntityView(@Mandatory MainWindow mainWindow, @Mandatory Object entity) {
        this.entity = entity;

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
                    panel.add(field.getButtons(), new GridSettingsFactory().create(2, y, 0, 0, PADDING));
                    fields.addLast(field);
                    y++;
                }

                // todo - handle other field types
            }

            if(entityField.isAnnotationPresent(Part.class)){
                if(!Iterable.class.isAssignableFrom(entityField.getField().getType())){
                    EntityFieldPartSelect field = new EntityFieldPartSelect(mainWindow, entity, entityField);
                    panel.add(field.getLabel(), new GridSettingsFactory().create(0, y, 0, 0, PADDING));
                    panel.add(field.getTextField(), new GridSettingsFactory().create(1, y, 1, 0, PADDING));
                    panel.add(field.getButtons(), new GridSettingsFactory().create(2, y, 0, 0, PADDING));
                    fields.addLast(field);
                    y++;
                }

                // todo - handle other field types
            }

            if(entityField.isAnnotationPresent(Link.class)){
                // todo - handle other field types
            }
        }

        // dummy alignment panel
        panel.add(new JPanel(), new GridSettingsFactory().create(0, y, 0, 1, 0));

        setViewportView(panel);
    }

    @Override
    public void refresh() {
        for(RefreshableView field : fields){
            field.refresh();
        }
    }

    @Override
    public Object getObject() {
        return entity;
    }
}
