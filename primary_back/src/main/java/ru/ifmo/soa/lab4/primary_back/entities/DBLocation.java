package ru.ifmo.soa.lab4.primary_back.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.ifmo.soa.lab4.primary_back.data.gen.Location;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigInteger;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Getter
@XmlRootElement(name = "location")
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "location")
public class DBLocation {

    public DBLocation(Integer x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Id
    @XmlTransient
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // в модели отсутствует

    @Column(nullable = false)
    private Integer x; //Поле не может быть null

    private float y;

    private float z;

    public void update(Location data) {
        this.x = data.getX().intValue();
        this.y = data.getY();
        this.z = data.getZ();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBLocation that = (DBLocation) o;
        return id == that.id && Float.compare(that.y, y) == 0 && Float.compare(that.z, z) == 0 && Objects.equals(x, that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, z);
    }

    public static DBLocation fromLocation(Location location){
        return new DBLocation(location.getX().intValue(), location.getY(), location.getZ());
    }

    public Location toLocation(){
        Location location = new Location();
        location.setX(BigInteger.valueOf(this.getX()));
        location.setY(this.getY());
        location.setZ(this.getZ());
        return location;
    }
}
