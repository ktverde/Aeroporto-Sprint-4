package br.com.compass.services;

import br.com.compass.dao.PlanesDao;
import br.com.compass.exception.IdNotFoundedException;
import br.com.compass.models.Plane;

import java.util.List;

public class FlightsService {


    private PlanesDao planesDao =new PlanesDao();

    public List<Plane> getAllFlightsJson() {
        return planesDao.getAllFlights();
    }

    public Plane getSelectedFlightJson(int id) {
        Plane plane = planesDao.getFlightById(id);
        if(plane == null)
            throw new IdNotFoundedException("Id not found");
        return plane;
    }
}
