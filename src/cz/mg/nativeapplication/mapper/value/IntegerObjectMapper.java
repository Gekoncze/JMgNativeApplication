package cz.mg.nativeapplication.mapper.value;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.mapper.ValueObjectMapper;


public class IntegerObjectMapper implements ValueObjectMapper<Integer> {
    @Override
    public @Mandatory boolean isApplicable(@Mandatory Object object) {
        return object.getClass() == Integer.class;
    }

    @Override
    public @Mandatory String getName() {
        return Integer.class.getSimpleName();
    }

    @Override
    public Integer create(@Optional String value) {
        return Integer.parseInt(value);
    }

    @Override
    public @Optional String getValue(@Mandatory Integer object) {
        return Integer.toString(object);
    }
}
