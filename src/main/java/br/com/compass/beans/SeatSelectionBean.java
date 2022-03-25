package br.com.compass.beans;

import br.com.compass.models.Plane;
import jakarta.enterprise.inject.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class SeatSelectionBean {

    private Plane plane =new Plane();
    private int selectedValue;

    public List<Map.Entry<Integer,Boolean>> getSeatsList(){
        return new ArrayList<>(plane.getSeats().entrySet());
    }

    public void save(){
        plane.getSeats().put(selectedValue,false);
        System.out.println(plane.getSeats().get(selectedValue));

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

    public void setSelectedValue(int selectedValue) {
        this.selectedValue = selectedValue;
    }
}
