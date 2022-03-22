package br.com.compass.dao;



import br.com.compass.factory.EManagerFactory;
import br.com.compass.models.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDao
{
    private EntityManager em;

    public UserDao(){
        this.em = EManagerFactory.getEm();
    }

    public void save(User user){
        em.getTransaction().begin();
        this.em.persist(user);
        em.getTransaction().commit();
    }

    public List<User> readAll(){
        String jpql = "SELECT s FROM User s";
        return em.createQuery(jpql, User.class).getResultList();
    }
    public User readId(Long id){
        String jpql = "SELECT u FROM User u WHERE u.userId = :id";
        return em.createQuery(jpql, User.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    public User readName(String username){
        try{
            String jpql = "SELECT u FROM User u WHERE u.username = :username";
            return em.createQuery(jpql, User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
    public void update(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }
}
