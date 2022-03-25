package br.com.compass.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="planes")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private FlightCourse flightCourse;
    @ElementCollection
    @CollectionTable(name = "planes_seats_mapping",
            joinColumns = {@JoinColumn(name = "plane_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "seat_number")
    @Column(name = "avilable")
    private Map<Integer, Boolean> seats;

    boolean editable;

    public boolean isEditable() {
        return editable;
    }
    public void setEditable(boolean editable) {
        this.editable = editable;
    }


    public Plane() {
        seats=new HashMap<Integer,Boolean>();
        populateSeats();
    }

    private void populateSeats(){
        for(int i = 1; i<=186; i++) {
            this.seats.put(i, true);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FlightCourse getFlightCourse() {
        return flightCourse;
    }

    public void setFlightCourse(FlightCourse flightCourse) {
        this.flightCourse = flightCourse;
    }

    public Map<Integer, Boolean> getSeats() {
        return seats;
    }

    public void setSeats(Map<Integer, Boolean> seats) {
        this.seats = seats;

    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", flightCourse=" + flightCourse +
                ", seats=" + seats +
                '}';
    }

}
