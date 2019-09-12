package fr.formation.inti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.formation.inti.entities.Employee;

/**
 * CRUD
 * @author IN-LL-037
 *
 */
public class EmployeeDaoImpl implements IEmployeeDao{

	private static IEmployeeDao instance; // déclaration de l'instance
	protected EntityManager em;
	
	
	public static IEmployeeDao getInstance() { // instanciation de l'instance ssi elle n'existe pas encore
		if (instance == null) {
			instance = new EmployeeDaoImpl();
		}
		return instance;
	}
	
	// constructeur faisant appel à la méthode getEntityManager
	private EmployeeDaoImpl() {
		em = getEntityManager();	
	}
	
	private EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		if (em == null) {
			em = emf.createEntityManager();
		}
		return em;
	}
	
	
	public void closeEntityManager() {
		em.close();
	}
 	
		
	/**
	 * Enregistre tous les employés de la table SQL bd.employee dans une ArrayList
	 */
	public List<Employee> getAll() {
		Query query = em.createQuery("Select e from Employee e");
		List<Employee> listEmp = query.getResultList();
		return listEmp;
	}

	/**
	 * Enregistre un nouvel employé dans la table SQL bd.employee et retourne son ID
	 */
	public Integer saveEmployee(Employee e) {
				
		try {
			em.getTransaction().begin();		
			em.persist(e);
//			em.flush();
			em.getTransaction().commit();
		} catch (Exception e1) {
			e1.printStackTrace();
			em.getTransaction().rollback();
		}	
		return e.getEmpID();
	}

	/**
	 * Modifie le first_name et le last_name d'un employé sur la table SQL bd.employee
	 */
	public void updateEmployee(Employee e) {	
		try {
			em.getTransaction().begin();;
			em.merge(e);
			em.getTransaction().commit();
		} catch (Exception e1) {
			e1.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	/**
	 * Supprime un employé dans la table SQL bd.employee
	 */
	public void delete(Employee e) {
		try {
			em.getTransaction().begin();
			e = em.find(Employee.class, e.getEmpID()); //verifie que l'employe existe
			em.remove(e);	
			em.getTransaction().commit();
		} catch (Exception e1) {
			e1.printStackTrace();
			em.getTransaction().rollback();
		}
	}
	
	public void deleteById(Integer id) {
		try {
			Employee e = getByID(id);
			delete(e);
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
	}
	
	/**
	 * Retourne un objet de type Employee correspondant dans la table SQL bd.employee à un ID donné 
	 */
	public Employee getByID(Integer id) {
		return em.find(Employee.class, id);
	}
	
	public void deleteRangeId(Integer idMin, Integer idMax) {
		Employee e;
		try {
			for (int i = idMin; i<= idMax; i++) {
				if (em.find(Employee.class, i) == null) {
					continue;
				}
				e = getByID(i);
				delete(e);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
	}
}
