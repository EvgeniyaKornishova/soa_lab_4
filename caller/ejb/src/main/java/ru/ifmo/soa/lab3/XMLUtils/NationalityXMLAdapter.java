package ru.ifmo.soa.lab3.XMLUtils;

import ru.ifmo.soa.lab3.data.Country;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class NationalityXMLAdapter extends XmlAdapter<String, Country> {
    @Override
    public Country unmarshal(String s) throws Exception {
            return Country.valueOf(s);
    }

    @Override
    public String marshal(Country country) throws Exception {
        return country.toString();
    }
}
