/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat3.jpademo.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author Jacob
 */
public class Tester {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        
        Person p1 = new Person("Jacob", 1997);
        Person p2 = new Person("Emil", 1970);
        
        Address a1 = new Address("Gurrehaven 21", 3200, "helsinge");
        Address a2 = new Address("bernhard bangs alle 8", 2000, "Frederiksberg");
        
        p1.setAddress(a1);
        p2.setAddress(a2);
        
        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        Fee f3 = new Fee(150);
        
        p1.AddFee(f1);
        p2.AddFee(f2);
        p2.AddFee(f3);
        
        SwimStyle s1 = new SwimStyle("Crawl");
        SwimStyle s2 = new SwimStyle("Butterfly");
        SwimStyle s3 = new SwimStyle("Breast Stroke");
        
        p1.AddSwimStyle(s1);
        p1.AddSwimStyle(s2);
        p2.AddSwimStyle(s3);
        
        em.getTransaction().begin();
            em.persist(p1);
            em.persist(p2);
        em.getTransaction().commit();
        
        em.close();
        
        System.out.println("p1: " + p1.getName() + ", " + p1.getP_id());
        System.out.println("p2: " + p2.getName() + ", " + p2.getP_id());
        System.out.println("Jacobs addresse: " + p1.getAddress().getStreet());
        
        System.out.println(" lad os se om 2-vejs virker: " + a1.getPerson().getName());
        
        System.out.println("hvem har betalt f2? det har: " + f2.getPerson().getName());
        
        System.out.println("hvad har emil betalt");
        
        TypedQuery<Fee> q1 = em.createQuery("SELECT f FROM Fee f", Fee.class);
        List<Fee> fees = q1.getResultList();
        
        for (Fee f: fees){
            System.out.println(f.getPerson().getName() + ": " + f.getAmount() + "kr: " + f.getPaydate());
        }
        
    }
    
}
