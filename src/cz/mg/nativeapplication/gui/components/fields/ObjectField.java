package cz.mg.nativeapplication.gui.components.fields;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.explorer.Explorer;
import cz.mg.entity.explorer.services.ReadService;
import cz.mg.entity.explorer.services.UpdateService;
import cz.mg.nativeapplication.gui.components.other.Refreshable;
import cz.mg.nativeapplication.gui.ui.enums.alignment.UiAlignment;
import cz.mg.nativeapplication.gui.ui.controls.UiPanel;


public abstract @Utility class ObjectField extends UiPanel implements Refreshable {
    private static final int BORDER = 0;
    private static final int PADDING = 2;

    private final @Mandatory @Shared ReadService readService = new ReadService();
    private final @Mandatory @Shared UpdateService updateService = new UpdateService();

    private final @Mandatory @Link Explorer explorer;
    private final @Mandatory @Link Object object;
    private final @Value int index;
    private final @Mandatory @Link Class type;

    protected ObjectField(
        @Mandatory Explorer explorer,
        @Mandatory Object object,
        int index,
        @Mandatory Class type
    ) {
        super(BORDER, PADDING, UiAlignment.MIDDLE);
        this.explorer = explorer;
        this.object = object;
        this.index = index;
        this.type = type;
    }

    public @Mandatory Explorer getExplorer() {
        return explorer;
    }

    public @Mandatory Class getType() {
        return type;
    }

    public final @Optional Object getValue(){
        return readService.read(object, index);
    }

    public void setValue(@Optional Object value) {
        updateService.update(explorer, object, index, value);
        refresh();
    }
}
