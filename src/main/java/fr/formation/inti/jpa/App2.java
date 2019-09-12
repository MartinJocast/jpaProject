package fr.formation.inti.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.formation.inti.entities.Department;

public class App2 {

	public static void main(String[] args) {

		System.out.println("Running App...");
		
		System.out.println("Create persistence manager");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		EntityManager em = emf.createEntityManager();
		
		Department dpt = new Department();
		dpt.setName("Seine et Marne");
		
		System.out.println("Persist entities in a transaction");
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.persist(dpt);
		
		Department dept2 = em.find(Department.class, 7);
		
		transaction.commit();
	}

}
