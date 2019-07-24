/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.newsgroup.services;

import com.mycompany.newsgroup.entities.UserProfile;
import com.mycompany.newsgroup.utils.AuthenticationUtils;
import com.mycompany.newsgroup.utils.Message;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author denzel
 */
@Stateful
public class SignupService {
   
//    private EntityManagerFactory emf=Persistence.createEntityManagerFactory("com.mycompany_newsGroup_war_1.0-SNAPSHOTPU");
//    EntityManager em=emf.createEntityManager();
    @PersistenceContext(unitName="com.mycompany_newsGroup_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    private AuthenticationUtils authenticationUtils=new AuthenticationUtils();
    String newId="n";
    
    public Message addUserAccount(UserProfile userProfile){
        System.out.println("-------------->Start");
        String salt=authenticationUtils.generateSalt(30);
        String securedPassword=null;
        
        try{
        securedPassword=authenticationUtils.generateSecurePassword(userProfile.getPassword(), salt);
        }catch(InvalidKeySpecException ex){
            Logger.getLogger(SignupService.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        userProfile.setPassword(securedPassword);
        userProfile.setSalt(salt);
  
        try{
            System.out.println("First line in try and catch");
//            em.getTransaction().begin();
            em.persist(userProfile);
            
            System.out.println("Thid line in carxh");
            Query query=em.createQuery("SELECT u.id FROM UserProfile u WHERE u.email=:email");
            query.setParameter("email", userProfile.getEmail());
            Object id=query.getSingleResult();
            newId=id.toString();
//            em.getTransaction().commit();
//            emf.close();
//            em.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Message(201,"Created",newId);
    }
    public void updateUserAccountByPassword(String password, String email){
        UserProfile userProfile=em.find(UserProfile.class, email);
        userProfile.setPassword(password);
    }
    public void deleteUserAccount(String email){
        UserProfile userProfile=em.find(UserProfile.class, email);
        em.remove(userProfile);
    }
}
