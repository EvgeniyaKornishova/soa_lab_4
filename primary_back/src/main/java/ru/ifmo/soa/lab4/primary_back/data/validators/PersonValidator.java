package ru.ifmo.soa.lab4.primary_back.data.validators;


import ru.ifmo.soa.lab4.primary_back.data.gen.InPerson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonValidator implements Validator<InPerson> {

    private final CoordinatesValidator coordinatesValidator;
    private final LocationValidator locationValidator;

    private final List<String> nullableFields;
    private final List<String> prohibitedFields;

    public PersonValidator() {
        coordinatesValidator = new CoordinatesValidator();
        locationValidator = new LocationValidator();
        nullableFields = Arrays.asList("eyeColor", "hairColor");
        prohibitedFields = Arrays.asList("id", "creationDate");
    }

    @Override
    public List<String> validate(InPerson person) throws IllegalAccessException{
        List<String> errorList = new ArrayList<>();

        for (Field field : InPerson.class.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(person) == null) {
                if (!(nullableFields.contains(field.getName()) || prohibitedFields.contains(field.getName())))
                    errorList.add((String.format("person %s isn't specified or have a wrong type", field.getName())));
            } else {
                if (prohibitedFields.contains(field.getName())){
                    field.set(person, null);
                }
            }
        }

        if (person.getName() != null && person.getName().trim().length() == 0) {
            errorList.add("person name should be not empty");
        }

        if (person.getHeight() <= 0.0f) {
            errorList.add("person height should be bigger than 0");
        }

        errorList.addAll(coordinatesValidator.validate(person.getCoordinates()));
        errorList.addAll(locationValidator.validate(person.getLocation()));


        return errorList;
    }
}
