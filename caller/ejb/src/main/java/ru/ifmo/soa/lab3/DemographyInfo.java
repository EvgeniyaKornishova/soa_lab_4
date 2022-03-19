package ru.ifmo.soa.lab3;

import ru.ifmo.soa.lab3.data.Color;

import javax.ejb.Remote;

@Remote
public interface DemographyInfo {
  Long getAmountOfPeopleByHairColor(Color hair_color);
  Double getPercentOfPeopleByHairColor(Color hair_color);
}
