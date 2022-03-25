package br.com.compass.beans;

import br.com.compass.dao.PlanesDao;
import br.com.compass.models.Plane;
import jakarta.enterprise.inject.Model;
import jakarta.faces.annotation.FacesConfig;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.*;

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

    private String mainPlanesId;
    private String otherPlanesId;

    public void loadPlanes(){
        System.out.println("alou");
        System.out.println(mainPlanesId + " | " + otherPlanesId);
        String[] aux1 = mainPlanesId.split("-");
        String[] aux2 = otherPlanesId.split("-");

        for (String s: aux1) {
            mainPlanes.add(planesDao.getFlightById(Integer.parseInt(s)));
        }
        for (String s: aux2) {
            otherPlanes.add(planesDao.getFlightById(Integer.parseInt(s)));
        }
    }

    public boolean test(){
        return mainPlanes.isEmpty();
    }

    public String redirect(int id){
        Response.seeOther(URI.create("http://localhost:8080/api/order/makeTicket/"+id)).build();
        return "";
    }

    public List<Plane> getMainPlanes() { return mainPlanes; }
    public void setMainPlanes(List<Plane> mainPlanes) { this.mainPlanes = mainPlanes; }

    public List<Plane> getOtherPlanes() { return otherPlanes; }
    public void setOtherPlanes(List<Plane> otherPlanes) { this.otherPlanes = otherPlanes; }

    public String getMainPlanesId() { return mainPlanesId; }
    public void setMainPlanesId(String mainPlanesId) { this.mainPlanesId = mainPlanesId; }

    public String getOtherPlanesId() { return otherPlanesId; }
    public void setOtherPlanesId(String otherPlanesId) { this.otherPlanesId = otherPlanesId; }
}
