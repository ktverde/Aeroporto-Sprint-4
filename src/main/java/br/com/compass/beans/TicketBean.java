package br.com.compass.beans;

import br.com.compass.dao.PlanesDao;
import br.com.compass.models.Plane;
import jakarta.enterprise.inject.Model;
import jakarta.faces.annotation.FacesConfig;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.*;
import org.jboss.weld.context.activator.ActivateRequestContext;

import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.*;

@FacesConfig
@Model
public class TicketBean
{

    @Inject
    private PlanesDao planesDao;

    private List<Plane> mainPlanes = new ArrayList<>();
    private List<Plane> otherPlanes = new ArrayList<>();

    private String planeId;
    private String selectedValue;

    private String mainPlanesId;
    private String otherPlanesId;

    public void loadPlanes(){
        String[] aux1, aux2;
        System.out.println("alou");
        System.out.println(mainPlanesId + " | " + otherPlanesId);
        if(mainPlanesId != null) {
            aux1 = mainPlanesId.split("-");
            for (String s: aux1) {
                mainPlanes.add(planesDao.getFlightById(Integer.parseInt(s)));
            }
        }
        if(otherPlanesId != null) {
            aux2 = otherPlanesId.split("-");
            for (String s : aux2) {
                otherPlanes.add(planesDao.getFlightById(Integer.parseInt(s)));
            }
        }
    }

    public boolean test1(){
        return mainPlanes.isEmpty() && otherPlanes.isEmpty();
    }
    public boolean test2(){
        return mainPlanes.isEmpty() && !otherPlanes.isEmpty();
    }

    public Plane getPlaneForIndex(int index){
        return mainPlanes.get(index);
    }

    public List<Map.Entry<Integer,Boolean>> getSeatsList(Plane plane){
        if(plane!=null)
            return new ArrayList<>(plane.getSeats().entrySet());
        else
            return new ArrayList<>();
    }

    public List<Plane> getMainPlanes() { return mainPlanes; }
    public void setMainPlanes(List<Plane> mainPlanes) { this.mainPlanes = mainPlanes; }

    public List<Plane> getOtherPlanes() { return otherPlanes; }
    public void setOtherPlanes(List<Plane> otherPlanes) { this.otherPlanes = otherPlanes; }

    public String getMainPlanesId() { return mainPlanesId; }
    public void setMainPlanesId(String mainPlanesId) { this.mainPlanesId = mainPlanesId; }

    public String getOtherPlanesId() { return otherPlanesId; }
    public void setOtherPlanesId(String otherPlanesId) { this.otherPlanesId = otherPlanesId; }

    public String getSelectedValue() { return selectedValue; }
    public void setSelectedValue(String selectedValue) { this.selectedValue = selectedValue; }

    public String getPlaneId() { return planeId; }
    public void setPlaneId(String planeId) { this.planeId = planeId; }

}
