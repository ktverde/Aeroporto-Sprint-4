package br.com.compass.models;

import com.google.gson.Gson;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

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
    @Column(name = "available")
    private Map<Integer, Boolean> seats;

    private BigDecimal value;
    private Date date;

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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public FlightCourse getFlightCourse() { return flightCourse; }
    public void setFlightCourse(FlightCourse flightCourse) { this.flightCourse = flightCourse; }

    public Map<Integer, Boolean> getSeats() { return seats; }
    public void setSeats(Map<Integer, Boolean> seats) { this.seats = seats; }

    public String getDateFormated() {

        SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        sp.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sp.format(date);
    }
    public Date getDate() { return date; }
    public void setDate(Date date){ this.date = date; }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", flightCourse=" + flightCourse +
                ", seats=" + seats +
                '}';
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
