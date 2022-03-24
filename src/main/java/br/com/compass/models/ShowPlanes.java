package br.com.compass.models;

import java.util.ArrayList;
import java.util.List;

public class ShowPlanes
{
    private List<FlightCourse> mainPlanes = new ArrayList<>();
    private List<FlightCourse> otherPlanes = new ArrayList<>();

    public ShowPlanes() {
    }

    public void addMain(FlightCourse fc){
        mainPlanes.add(fc);
    }
    public void addOther(FlightCourse fc){
        otherPlanes.add(fc);
    }


    public List<FlightCourse> getMainPlanes() { return mainPlanes; }
    public void setMainPlanes(List<FlightCourse> mainPlanes) { this.mainPlanes = mainPlanes; }

    public List<FlightCourse> getOtherPlanes() { return otherPlanes; }
    public void setOtherPlanes(List<FlightCourse> otherPlanes) { this.otherPlanes = otherPlanes; }
}
