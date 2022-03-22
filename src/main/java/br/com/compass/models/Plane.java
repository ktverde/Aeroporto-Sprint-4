package br.com.compass.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="planes")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private FlightCourse flightCourse;
    @OneToMany
    @Size(max=186)
    private List<Seat> seats;

    public Plane() {
        this.seats=new ArrayList<>();
        populateSeats();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public FlightCourse getFlightCourse() {
        return flightCourse;
    }

    public void setFlightCourse(FlightCourse flightCourse) {
        this.flightCourse = flightCourse;
    }

    private void populateSeats(){
        int i=0;
        while(i<186){
            Seat seat= new Seat();
            seats.add(seat);
            i++;
        }
    }
}
