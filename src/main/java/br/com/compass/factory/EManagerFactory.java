package br.com.compass.factory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EManagerFactory
{
    private static final EntityManager em = Persistence.createEntityManagerFactory("aeroporto").createEntityManager();

    public static EntityManager getEm(){
        return em;
    }
}
