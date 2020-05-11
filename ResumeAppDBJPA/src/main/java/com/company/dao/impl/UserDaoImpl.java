package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.entity.User;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {
    
    @Override
    public List<User> getAll(String name,String surname,Integer nationalityId) {
        
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("resumeappPU");
            EntityManager entitymanager = emfactory.createEntityManager();
                        
            String jpql = "select u from User u where 1=1";

           // String sql = "select u.*,c.name as birthplace, n.nationality as nationality from USERS as u left join country as n on u.nationality_id=n.id left join country as c on u.birthplace_id=c.id where 1=1";
            
            if (name!=null && !name.trim().isEmpty()) {
                jpql+=" and u.name= :name";
            }
            if (surname!=null && !surname.trim().isEmpty()) {
                jpql+=" and u.surname= :surname";
            }
            if (nationalityId!=null) {
                jpql+=" and u.nationalityId.id= :nId";
            }
            
            Query query = entitymanager.createQuery(jpql, User.class);

            if (name!=null && !name.trim().isEmpty()){
                query.setParameter("name", name);
            }
            if (surname!=null && !surname.trim().isEmpty()){
                query.setParameter("surname", surname);
            }
            if (nationalityId!=null) {
                query.setParameter("nId", nationalityId);
            }
            
            List<User> user = query.getResultList();
            
            entitymanager.close();
            emfactory.close();
            
            return user;
    }
    
    @Override
    public User getById(int userID) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("resumeappPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        
        User u = entitymanager.find(User.class, userID);
        
        entitymanager.close();
        emfactory.close();
        return u;
    }
    
     @Override
    public boolean updateUser(User u) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("resumeappPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        
        entitymanager.getTransaction().begin();
        entitymanager.merge(u);
        entitymanager.getTransaction().commit();
        
        entitymanager.close();
        emfactory.close();
        
        return true;
    }
    
    @Override
     public boolean removeUser(int id) {
       EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("resumeappPU"); // factory agir prosesdirdi ona gore her defe acilib baglananda problem olur performans uchun
       EntityManager entitymanager = emfactory.createEntityManager();
       //User u = getById(id); bele yazsam ishlemeyecek chunki bu User classi jpa terefinden yaradilmayib ve entity bunun imkanlarindan istifade ede bilmez ona gore entitiy oz funksiyasi ile tapmalidi useri
       entitymanager.getTransaction().begin(); 
       //select emeliyyatindan ferqli olaraq, update,insert,delete emeliyyatlari bazadi bir sheyler deyishir. Ona gore bunlari etmezden evvel transaction yaradilmali bitende ise commit etmeliyik jpada.
       User u = entitymanager.find(User.class, id);
       entitymanager.remove(u);
       entitymanager.getTransaction().commit();
       
       entitymanager.close();
       emfactory.close();
       
       return true;
     }

    private BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public int addUser(User u) {
        u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));
        
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("resumeappPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        
        entitymanager.getTransaction().begin();
        entitymanager.persist(u);
        entitymanager.getTransaction().commit();
        
        entitymanager.close();
        emfactory.close();
        
        return 1;
    }
    

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("resumeappPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        
        Query query = entitymanager.createQuery("select u from User u where u.email= :email, u.password= :password",User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
      //  query.getSingleResult(); tekce neticeni gonderir, yeni eminsen ki bir dene column qayidacaq, o zaman yazmaq lazimdir. Birden chox olursa artiq error verecek
        List<User> user = query.getResultList();
        
        entitymanager.close();
        emfactory.close();
        
        if(user.size() == 1)
        {
            return user.get(0);
        }
        return null;
    }

//    @Override
//    public User getUserByEmail(String email) {
//       
//        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("resumeappPU");
//        EntityManager entitymanager = emfactory.createEntityManager();
//        
//        Query query = entitymanager.createQuery("select u from User u where u.email= :email", User.class);
//        query.setParameter("email", email);
//        List<User> user = query.getResultList();
//        
//        entitymanager.close();
//        emfactory.close();
//        if(user.size() == 1)
//        {
//            return user.get(0);
//        }
//        
//        return null;
//        // PreparedStatement preparedStatement = connection.prepareStatement("select u.*,c.name as birthplace, n.nationality as nationality from USERS as u left join country as n on u.nationality_id=n.id left join country as c on u.birthplace_id=c.id where email=?");
//    }
    
    
    //CREATE BUILDER
//    @Override
//    public User getUserByEmail (String email) {
//        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("resumeappPU");
//        EntityManager entitymanager = emfactory.createEntityManager();
//        
//        CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();
//        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
//        Root<User> postRoot = criteriaQuery.from(User.class);
//        
//        CriteriaQuery<User> q2 = criteriaQuery
//                .where(criteriaBuilder.equal(postRoot.get("email"), email));
//        
//        Query query = entitymanager.createQuery(q2);
//        
//        List<User> user = query.getResultList();
//        
//        entitymanager.close();
//        emfactory.close();
//        
//        if (user.size()==1) 
//        {
//            return user.get(0);
//        }
//        
//        return null;
//    }
    
    //NamedQuery
    @Override
    public User getUserByEmail (String email)
    {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("resumeappPU");
        EntityManager entityManager = emfactory.createEntityManager();
        
        Query query = entityManager.createNamedQuery("User.findByEmail",User.class);
        query.setParameter("email", email);
      
        List<User> user = query.getResultList();
        
        entityManager.close();
        emfactory.close();
        
        if(user.size()==1)
        {
            return user.get(0);
        }
        return null;
    }
    
    //NativeQuery
//    @Override
//    public User getUserByEmail (String email)
//    {
//        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("resumeappPU");
//        EntityManager entityManager = emfactory.createEntityManager();
//        
//        Query query = entityManager.createNativeQuery("select * from user where email=?",User.class);
//        query.setParameter("1", email);
//        List<User> user = query.getResultList();
//        
//        entityManager.close();
//        emfactory.close();
//        
//        if(user.size()==1)
//        {
//            return user.get(0);
//        }
//        return null;
//    }
}
