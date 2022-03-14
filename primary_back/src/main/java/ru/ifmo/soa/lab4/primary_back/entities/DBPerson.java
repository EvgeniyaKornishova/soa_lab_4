package ru.ifmo.soa.lab4.primary_back.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ifmo.soa.lab4.primary_back.data.gen.Color;
import ru.ifmo.soa.lab4.primary_back.data.gen.Country;
import ru.ifmo.soa.lab4.primary_back.data.gen.InPerson;
import ru.ifmo.soa.lab4.primary_back.data.gen.Person;

import javax.persistence.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "person")
public class DBPerson {

    public DBPerson(String name, DBCoordinates coordinates, LocalDateTime creationDate, float height, Color eyeColor, Color hairColor, Country nationality, DBLocation location) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @Column(columnDefinition = "TEXT NOT NULL CHECK (char_length(person.name) > 0)")
    private String name; //Поле не может быть null, Строка не может быть пустой

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_id")
    private DBCoordinates coordinates; //Поле не может быть null

    @Column(nullable = false, name = "creation_date")
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Column(columnDefinition = "REAL CHECK (height > 0)")
    private float height; //Значение поля должно быть больше 0

    private Color eyeColor; //Поле может быть null

    private Color hairColor; //Поле может быть null

    @Column(nullable = false)
    private Country nationality; //Поле не может быть null

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private DBLocation location; //Поле не может быть null

    public void update(InPerson data) {
        this.name = data.getName();
        this.coordinates.update(data.getCoordinates());
        this.height = data.getHeight();
        this.eyeColor = data.getEyeColor();
        this.hairColor = data.getHairColor();
        this.nationality = data.getNationality();
        this.location.update(data.getLocation());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBPerson dbPerson = (DBPerson) o;
        return getId() == dbPerson.getId() && Float.compare(dbPerson.getHeight(), getHeight()) == 0 && Objects.equals(getName(), dbPerson.getName()) && getCoordinates().equals(dbPerson.getCoordinates()) && getCreationDate().atZone(ZoneId.systemDefault()).toEpochSecond() == dbPerson.getCreationDate().atZone(ZoneId.systemDefault()).toEpochSecond() && getEyeColor() == dbPerson.getEyeColor() && getHairColor() == dbPerson.getHairColor() && getNationality() == dbPerson.getNationality() && getLocation().equals(dbPerson.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCoordinates().hashCode(), getCreationDate(), getHeight(), getEyeColor(), getHairColor(), getNationality(), getLocation().hashCode());
    }

    public static DBPerson fromInPerson(InPerson person){
        DBPerson dbPerson = new DBPerson(
                person.getName(),
                DBCoordinates.fromCoordinates(person.getCoordinates()),
                LocalDateTime.now(),
                person.getHeight(),
                person.getEyeColor(),
                person.getHairColor(),
                person.getNationality(),
                DBLocation.fromLocation(person.getLocation())
        );
        return dbPerson;
    }

    public Person toPerson(){
        Person person = new Person();
        person.setName(this.getName());
        person.setCoordinates(this.getCoordinates().toCoordinates());
        GregorianCalendar gc = new GregorianCalendar();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        String formattedDate = this.getCreationDate().format(formatter);
        try {
            XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(formattedDate);
            person.setCreationDate(calendar);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        person.setId(this.getId());
        person.setEyeColor(this.getEyeColor());
        person.setHairColor(this.getHairColor());
        person.setHeight(this.getHeight());
        person.setNationality(this.getNationality());
        person.setLocation(this.getLocation().toLocation());
        return person;
    }
}
