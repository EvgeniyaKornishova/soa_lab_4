package ru.ifmo.soa.lab4.primary_back.data.PersonExtensions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Paginator {
    private final Integer pageId;
    private final Integer pageSize;
}
