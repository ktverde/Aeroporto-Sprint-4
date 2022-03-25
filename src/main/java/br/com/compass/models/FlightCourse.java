package br.com.compass.models;

import jakarta.validation.constraints.NotBlank;

import javax.persistence.*;
import java.util.IdentityHashMap;

@Entity
@Table(name="flightCourses")
public class FlightCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String origin;
    @NotBlank
    private String destiny;

    public FlightCourse() {
    }

    public FlightCourse(String origin, String destiny) {
        this.destiny = destiny;
        this.origin = origin;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDestiny() {
        return destiny;
    }
    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "FlightCourse{" +
                "id=" + id +
                ", destiny='" + destiny + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}
