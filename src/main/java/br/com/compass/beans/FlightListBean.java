package br.com.compass.beans;

import br.com.compass.dao.FlightsDao;
import br.com.compass.models.Plane;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;


@Model
public class FlightListBean {

    private List<Plane> planes = new ArrayList<>();

    @Inject
    FlightsDao flightsDao;

    public List<Plane> getPlanes() {
        planes=flightsDao.getAllFlights();
        return planes;
    }

    public void deletePlane(Plane plane){
        flightsDao.delete(plane);
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }
}
