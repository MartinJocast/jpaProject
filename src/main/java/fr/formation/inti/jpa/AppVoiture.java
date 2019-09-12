package fr.formation.inti.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.formation.inti.entities.Department;
import fr.formation.inti.entities.Employee;
import fr.formation.inti.entities.Personne;
import fr.formation.inti.entities.Voiture;

public class AppVoiture {

	public static void main(String[] args) {

		System.out.println("Running App...");
		
		System.out.println("Create persistence manager");
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		EntityManager em = emf.createEntityManager();
				
		EntityTransaction transaction = em.getTransaction(); 
		transaction.begin();

		// instanciation voitures
		Voiture v1 = new Voiture();
		v1.setMarque("Fiat");
		Voiture v2 = new Voiture();
		v2.setMarque("Renault");
		Voiture v3 = new Voiture();
		v3.setMarque("Opel");
		
		// voitures --> persist
		em.persist(v1);
		em.persist(v2);
		em.persist(v3);

		// instanciation personnes
		Personne p1 = new Personne();
		p1.setFirstName("Prenom1");
		p1.setLastName("Nom1");
		Personne p2 = new Personne();
		p2.setFirstName("Prenom2");
		p2.setLastName("Nom2");
		
		// creation d'une liste de voitures
		List<Voiture> listVoit = new ArrayList<Voiture>();
		listVoit.add(v1);
		listVoit.add(v2);
		listVoit.add(v3);
		
		// attribution de cette liste à l'attribut listVoit des personnes p1 et p2
		p1.setListVoit(listVoit);
		p2.setListVoit(listVoit);
		
		// personnes --> persist : creation dans la BD des tables "personne", "voiture" et de la table d'association "voiture_personne"
		em.persist(p1);
		em.persist(p2);
		
//		Personne pers1 = em.find(Personne.class, 1);
//		for(Voiture voit : pers1.getListVoit())
//			System.out.println(voit);
		
		transaction.commit();
		
		em.close();
		emf.close();
	}
}
