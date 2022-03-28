package br.com.compass.beans;

import br.com.compass.dao.PlanesDao;
import br.com.compass.dao.TicketDao;
import br.com.compass.models.Plane;
import br.com.compass.models.Ticket;
import jakarta.enterprise.inject.Model;
import jakarta.faces.annotation.FacesConfig;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@FacesConfig
@Model
public class TicketBean
{

    @Inject
    private PlanesDao planesDao;
    @Inject
    private TicketDao ticketDao;

    private List<Plane> mainPlanes = new ArrayList<>();
    private List<Plane> otherPlanes = new ArrayList<>();

    private String planeId;
    private String selectedValue;

    private String mainPlanesId;
    private String otherPlanesId;

    private String ticketId;
    private Ticket ticket;

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

    public void loadTicket(){
        if(ticketId != null) this.ticket = ticketDao.readId(Integer.parseInt(ticketId));
    }

    public boolean test1(){
        return mainPlanes.isEmpty() && otherPlanes.isEmpty();
    }
    public boolean test2(){
        return mainPlanes.isEmpty() && !otherPlanes.isEmpty();
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

    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }

    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }
}
