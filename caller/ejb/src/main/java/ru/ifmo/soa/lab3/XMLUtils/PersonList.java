package ru.ifmo.soa.lab3.XMLUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.ifmo.soa.lab3.data.Person;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "persons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PersonList {

    @XmlAttribute
    Long count;

    @XmlElement(name="person")
    List<Person> persons;
}
