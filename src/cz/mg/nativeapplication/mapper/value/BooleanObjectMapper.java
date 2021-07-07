package cz.mg.nativeapplication.mapper.value;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.mapper.ValueObjectMapper;


public class BooleanObjectMapper implements ValueObjectMapper<Boolean> {
    @Override
    public @Mandatory boolean isApplicable(@Mandatory Object object) {
        return object.getClass() == Boolean.class;
    }

    @Override
    public @Mandatory String getName() {
        return Boolean.class.getSimpleName();
    }

    @Override
    public Boolean create(@Optional String value) {
        return Boolean.parseBoolean(value);
    }

    @Override
    public @Optional String getValue(@Mandatory Boolean object) {
        return Boolean.toString(object);
    }
}
