package ru.ifmo.soa.lab4.primary_back.data.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SorterValidator implements Validator<String> {
    private static final List<String> possibleValues = new ArrayList<>(Arrays.asList(
            "id",
            "name",
            "height",
            "eye_color",
            "hair_color",
            "nationality",
            "creation_date",
            "location_x",
            "location_y",
            "location_z",
            "coordinates_x",
            "coordinates_y"
    ));

    @Override
    public List<String> validate(String sort) {
        List<String> errorList = new ArrayList<>();

        if (sort != null && !possibleValues.contains(sort))
            errorList.add("unknown sort field");

        return errorList;
    }

    public String format(String sort) {
        if (sort.startsWith("location") || sort.startsWith("coordinates"))
            sort = sort.replace('_', '.');
        else {
            List<String> keywords = Arrays.asList(sort.split("_"));
            StringBuilder sortBuilder = new StringBuilder(keywords.get(0));
            for (int i = 1; i < keywords.size(); i++)
                sortBuilder.append(keywords.get(i).substring(0, 1).toUpperCase()).append(keywords.get(i).substring(1));
            sort = sortBuilder.toString();
        }
        return sort;
    }
}
