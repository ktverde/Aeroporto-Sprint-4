package br.com.compass.resources;

import br.com.compass.dao.PlanesDao;
import br.com.compass.models.Plane;
import jakarta.inject.Inject;

import javax.persistence.Id;
import java.util.List;

public class FlightsService {


    private PlanesDao planesDao =new PlanesDao();

    public List<Plane> getAllFlightsJson() {
        return planesDao.getAllFlights();
    }

    public Plane getSelectedFlightJson(int id) {
        return planesDao.getFlightById(id);
    }
}
