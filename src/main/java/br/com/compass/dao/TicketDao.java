package br.com.compass.dao;



import br.com.compass.factory.EManagerFactory;
import br.com.compass.models.Ticket;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public class TicketDao
{
    private EntityManager em;

    public TicketDao(){
        this.em = EManagerFactory.getEm();
    }

    public void save(Ticket ticket){
        em.getTransaction().begin();
        this.em.persist(ticket);
        em.getTransaction().commit();
    }

    public List<Ticket> readAll(){
        String jpql = "SELECT t FROM Ticket t";
        return em.createQuery(jpql, Ticket.class).getResultList();
    }
    public Ticket readId(int id){
        String jpql = "SELECT t FROM Ticket t WHERE t.id = :id";
        return em.createQuery(jpql, Ticket.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void update(Ticket ticket) {
        em.getTransaction().begin();
        em.merge(ticket);
        em.getTransaction().commit();
    }
}
