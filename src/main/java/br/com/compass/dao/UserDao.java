package br.com.compass.dao;



import br.com.compass.factory.EManagerFactory;
import br.com.compass.models.User;

import javax.persistence.EntityManager;
import java.util.Arrays;
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
            String jpql = "SELECT u FROM User u WHERE u.username = ?1 or u.email = ?2";
            System.out.println(username);
            return em.createQuery(jpql, User.class)
                    .setParameter(1, username)
                    .setParameter(2, username)
                    .getSingleResult();
        }catch(Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
            return null;
        }
    }
    public void update(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }
}
