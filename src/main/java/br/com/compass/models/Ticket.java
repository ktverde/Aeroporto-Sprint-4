package br.com.compass.models;

import javax.persistence.*;
import java.util.Date;
import java.time.Instant;
import java.util.Calendar;

@Entity
@Table(name="tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private User client;
    @OneToOne
    private FlightCourse flightCourse;
    private Date emissionDate;
    private Date flightDate;

    private int planeId;
    private int seat;

    public Ticket() {
    }

    public Ticket(User client, FlightCourse flightCourse, Date flightDate, int planeId, int seat) {
        this.client = client;
        this.flightCourse = flightCourse;
        this.emissionDate = new Date();
        this.flightDate = flightDate;
        this.planeId = planeId;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    public FlightCourse getFlightCourse() {
        return flightCourse;
    }

    public void setFlightCourse(FlightCourse flightCourse) {
        this.flightCourse = flightCourse;
    }

    public Date getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(Date emissionDate) {
        this.emissionDate = emissionDate;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return String.format("Your Ticket:\n\t%s, %s\nid:%d\nFlight Information:\n\t\tPlane Id:%d\n\t\tOrigin:%s to Destiny:%s\n\t\tFlight date:%s, Seat:%d\nTicket Emission Date:%s"
                , client.getName(), client.getUsername(), planeId, flightCourse.getOrigin(), flightCourse.getDestiny(), flightDate, seat, emissionDate);
    }

}
