package br.com.compass.models;

import com.google.gson.Gson;

import javax.persistence.*;
import java.util.Date;

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
    private String flightDate;

    private int planeId;
    private int seat;

    public Ticket() {
    }

    public Ticket(User client, FlightCourse flightCourse, String flightDate, int planeId, int seat) {
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

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
    @Override
    public String toString() {
        return String.format("Your Ticket:\n\t%s, %s\n\tTicket id:%d\nFlight Information: %s to %s\n\tPlane Id:%d\n\tFlight date:%s, Seat:%d\nTicket Emission Date:%s"
                , client.getName(), client.getUsername(), client.getUserId(), flightCourse.getOrigin(), flightCourse.getDestiny(), planeId, flightDate.toString(), seat, emissionDate);
    }

}
