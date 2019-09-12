package fr.formation.inti.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.formation.inti.entities.manytomany.Category;
import fr.formation.inti.entities.manytomany.Stock;



public class AppManyToMany {

	public static void main(String[] args) {

		System.out.println("Running App...");
		
		System.out.println("Create persistence manager");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		EntityManager em = emf.createEntityManager();
		
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		Stock stock = new Stock();
        stock.setStockCode("7052");
        stock.setStockName("PADINI");
 
        Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
        Category category2 = new Category("INVESTMENT", "INVESTMENT COMPANY");
    
        Set<Category> categories = new HashSet<Category>();
        categories.add(category1);
        categories.add(category2);
        
        stock.setCategories(categories);
		
		em.persist(stock);
		transaction.commit();
		
		em.close();
		emf.close();
	}

}
