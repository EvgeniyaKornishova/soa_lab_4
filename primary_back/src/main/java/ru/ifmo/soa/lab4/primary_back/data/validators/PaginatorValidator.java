package ru.ifmo.soa.lab4.primary_back.data.validators;

import ru.ifmo.soa.lab4.primary_back.data.PersonExtensions.Paginator;

import java.util.ArrayList;
import java.util.List;

public class PaginatorValidator implements Validator<Paginator>{
    @Override
    public List<String> validate(Paginator paginator) {
        List<String> errorList = new ArrayList<>();

        if (paginator.getPageId() <= 0)
            errorList.add("page id should be positive not zero number");

        if (paginator.getPageSize() <= 0)
            errorList.add("page size should be positive not zero number");

        return errorList;
    }
}
