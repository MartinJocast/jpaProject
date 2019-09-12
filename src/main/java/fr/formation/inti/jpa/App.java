package fr.formation.inti.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.formation.inti.entities.Department;
import fr.formation.inti.entities.Employee;

public class App {
	public static void main(String[] args) {
		
		System.out.println("Running App...");
		
		System.out.println("Create persistence manager");
		
		// creation de la liaison entre SGBD et Java		
		// myApp: nom de l'unité de persistence
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("Create Entities: department");
		Department nord = new Department();
		nord.setName("Nord");
		Department centre = new Department();
		centre.setName("Centre");
		
		System.out.println("Persist entities in a transaction");
		
		EntityTransaction transaction = em.getTransaction(); // obligation de créer une transation, meme s'il n'y a qu'une requete
		// Begin
		transaction.begin();
		
//		em.persist(nord);
//		em.persist(centre);

		Department dept = em.find(Department.class, 1);
		for(Employee e : dept.getEmployees())
			System.out.println(e);
		
//		Employee emp = em.find(Employee.class, 4);
//		System.out.println(emp);
//		System.out.println(emp.getManager());

		
//		System.out.println(dept);
		// Commit
		transaction.commit();
	}
}
