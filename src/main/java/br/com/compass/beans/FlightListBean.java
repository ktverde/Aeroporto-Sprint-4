package br.com.compass.beans;

import br.com.compass.dao.FlightCourseDao;
import br.com.compass.dao.PlanesDao;
import br.com.compass.models.FlightCourse;
import br.com.compass.models.Plane;
import jakarta.enterprise.inject.Model;
import jakarta.faces.annotation.FacesConfig;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@FacesConfig
@Model
public class FlightListBean {

    private Plane plane;
    private FlightCourse flightCourse = new FlightCourse();
    private List<Plane> planes = new ArrayList<>();
    private int selectedValue;

    @Inject
    PlanesDao planesDao;
    @Inject
    FlightCourseDao flightCourseDao;


    public String saveAction(Plane plane){

        //get all existing value but set "editable" to false
        plane.setEditable(false);
        FlightCourse fc = flightCourseDao.verify(flightCourse);
        if(fc==null) {
            flightCourseDao.save(flightCourse);
            plane.setFlightCourse(flightCourse);
        }
        else {
            plane.setFlightCourse(fc);
        }
        planesDao.update(plane);
        //return to current page
        return null;

    }

/*    public String saveAction() {

        //get all existing value but set "editable" to false
        for (Plane plane : planes){
            plane.setEditable(false);
            FlightCourse fc = flightCourseDao.verify(flightCourse);
            if(fc==null) {
                flightCourseDao.save(flightCourse);
                plane.setFlightCourse(flightCourse);
            }
            else {
                plane.setFlightCourse(fc);
            }
            planesDao.update(plane);
        }
        //return to current page
        return null;

    }*/

    public String cancelAction(Plane plane){

        //get all existing value but set "editable" to false
        plane.setEditable(false);
        //return to current page
        return null;

    }

    public String editAction(Plane plane) {
        if (!plane.isEditable())
            plane.setEditable(true);
        else
            plane.setEditable(false);
        return null;
    }

    public List<Plane> getPlanes() {
        planes= planesDao.getAllFlights();
        return planes;
    }

    public void deletePlane(Plane plane){
        planesDao.delete(plane);
    }

    public List<Map.Entry<Integer,Boolean>> getMapInList(Plane plane){
        return new ArrayList<>(plane.getSeats().entrySet());
    }


    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    public FlightCourse getFlightCourse() {
        return flightCourse;
    }

    public FlightCourse getPlaneFlightCourse(Plane plane){
        return plane.getFlightCourse();
    }

    public void setFlightCourse(FlightCourse flightCourse) {
        this.flightCourse = flightCourse;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public int getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(int selectedvalue) {
        this.selectedValue = selectedvalue;
    }
}
