package cz.mg.nativeapplication.gui.components.entity;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.gui.MainWindow;
import cz.mg.nativeapplication.gui.components.ObjectView;
import cz.mg.nativeapplication.gui.components.RefreshableView;
import cz.mg.nativeapplication.gui.components.controls.UiPanel;
import cz.mg.nativeapplication.sevices.EntityClass;
import cz.mg.nativeapplication.sevices.EntityClassCache;
import cz.mg.nativeapplication.sevices.EntityField;

import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.MIDDLE;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Alignment.TOP;
import static cz.mg.nativeapplication.gui.components.controls.UiPanel.Fill.BOTH;


public @Utility class EntityView extends ObjectView {
    private static final int BORDER = 4;
    private static final int PADDING = 4;

    private final @Mandatory @Link Object entity;
    private final @Mandatory @Part List<RefreshableView> fields = new List<>();

    public EntityView(@Mandatory MainWindow mainWindow, @Mandatory Object entity) {
        this.entity = entity;

        UiPanel panel = new UiPanel(BORDER, PADDING, TOP);

        EntityClass entityClass = EntityClassCache.getInstance().get(entity.getClass());
        int y = 0;
        for(EntityField entityField : entityClass.getFields()){
            if(entityField.isAnnotationPresent(Link.class)){
                if(!Iterable.class.isAssignableFrom(entityField.getField().getType())){
                    EntityFieldLinkSelect field = new EntityFieldLinkSelect(mainWindow, entity, entityField);
                    panel.add(field.getLabel(), 0, y, 0, 0, MIDDLE, BOTH);
                    panel.add(field.getTextField(), 1, y, 1, 0, MIDDLE, BOTH);
                    panel.add(field.getButtons(), 2, y, 0, 0, MIDDLE, BOTH);
                    fields.addLast(field);
                    y++;
                }

                // todo - handle other field types
            }

            if(entityField.isAnnotationPresent(Part.class)){
                if(!Iterable.class.isAssignableFrom(entityField.getField().getType())){
                    EntityFieldPartSelect field = new EntityFieldPartSelect(mainWindow, entity, entityField);
                    panel.add(field.getLabel(), 0, y, 0, 0, MIDDLE, BOTH);
                    panel.add(field.getTextField(), 1, y, 1, 0, MIDDLE, BOTH);
                    panel.add(field.getButtons(), 2, y, 0, 0, MIDDLE, BOTH);
                    fields.addLast(field);
                    y++;
                }

                // todo - handle other field types
            }

            if(entityField.isAnnotationPresent(Link.class)){
                // todo - handle other field types
            }
        }

        panel.rebuild();
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
