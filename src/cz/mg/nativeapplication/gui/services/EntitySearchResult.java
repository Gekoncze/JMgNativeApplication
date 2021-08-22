package cz.mg.nativeapplication.gui.services;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.entity.EntityField;


public @Utility class EntitySearchResult {
    private final @Mandatory @Link EntityField entityField;
    private final @Mandatory @Link Object entity;

    public EntitySearchResult(@Mandatory EntityField entityField, @Mandatory Object entity) {
        this.entityField = entityField;
        this.entity = entity;
    }

    public EntityField getEntityField() {
        return entityField;
    }

    public Object getEntity() {
        return entity;
    }
}
