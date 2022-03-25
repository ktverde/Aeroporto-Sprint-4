package br.com.compass.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowPlanes
{
    private List<Plane> mainPlanes = new ArrayList<>();
    private List<Plane> otherPlanes = new ArrayList<>();
    public ShowPlanes() {
    }

    public ShowPlanes(List<Plane> mainPlanes, List<Plane> otherPlanes) {
        this.mainPlanes = mainPlanes;
        this.otherPlanes = otherPlanes;
    }

    public List<Plane> getMainPlanes() {
        return mainPlanes;
    }

    public void setMainPlanes(List<Plane> mainPlanes) {
        this.mainPlanes = mainPlanes;
    }

    public List<Plane> getOtherPlanes() {
        return otherPlanes;
    }

    public void setOtherPlanes(List<Plane> otherPlanes) {
        this.otherPlanes = otherPlanes;
    }
}
