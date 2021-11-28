package cz.mg.nativeapplication.gui.ui.controls.field.other;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Shared;
import cz.mg.nativeapplication.gui.ui.controls.UiHorizontalPanel;
import cz.mg.nativeapplication.gui.ui.controls.UiImage;
import cz.mg.nativeapplication.gui.ui.controls.field.base.UiFieldBase;

import java.awt.*;


public @Utility class UiFieldBaseWrapper extends UiHorizontalPanel {
    private static final int PADDING = 2;

    private final @Mandatory @Shared UiFieldImageProvider fieldImageProvider;
    private final @Mandatory @Shared UiImage imageComponent;
    private final @Mandatory @Shared UiFieldBase fieldBase;

    public UiFieldBaseWrapper(@Mandatory UiFieldImageProvider fieldImageProvider, @Mandatory UiFieldBase fieldBase) {
        super(0, PADDING, Alignment.LEFT);
        this.fieldImageProvider = fieldImageProvider;
        this.imageComponent = new UiImage();
        this.fieldBase = fieldBase;

        FontMetrics fontMetrics = fieldBase.getFontMetrics(fieldBase.getFont());
        imageComponent.setPreferredSize(new Dimension(fontMetrics.getHeight(), fontMetrics.getHeight()));
        add(imageComponent, 0, 0, Alignment.LEFT, Fill.BOTH);
        add(fieldBase, 1, 0, Alignment.LEFT, Fill.BOTH);
        rebuild();
    }

    public @Mandatory UiFieldBase getFieldBase() {
        return fieldBase;
    }

    public void lock(){
        fieldBase.lock();
    }

    public void unlock(){
        fieldBase.unlock();
    }

    public boolean isLocked() {
        return fieldBase.isLocked();
    }

    public @Optional Object getValue(){
        return fieldBase.getValue();
    }

    public void setValue(@Optional Object value){
        fieldBase.setValue(value);
        imageComponent.setImage(fieldImageProvider.get(value));
    }
}
