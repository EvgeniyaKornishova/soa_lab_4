package ru.ifmo.soa.lab4.primary_back.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.ifmo.soa.lab4.primary_back.data.gen.Coordinates;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Getter
@XmlRootElement(name = "coordinates")
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "coordinate")
public class DBCoordinates {

    public DBCoordinates(long x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Id
    @XmlTransient
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // в модели отсутствует

    @Column(columnDefinition = "REAL CHECK (coordinate.x > -375)")
    private long x; //Значение поля должно быть больше -375

    @Column(columnDefinition = "REAL NOT NULL CHECK (coordinate.y <= 796)")
    private Double y; //Максимальное значение поля: 796, Поле не может быть null

    public void update(Coordinates data) {
        this.x = data.getX();
        this.y = data.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBCoordinates that = (DBCoordinates) o;
        return id == that.id && x == that.x && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y);
    }

    public static DBCoordinates fromCoordinates(Coordinates coordinates){
        return new DBCoordinates(coordinates.getX(), coordinates.getY());
    }

    public Coordinates toCoordinates(){
        Coordinates coordinates = new Coordinates();
        coordinates.setX(this.getX());
        coordinates.setY(this.getY());
        return coordinates;
    }
}
