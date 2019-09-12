package fr.formation.inti.jpa;

import java.util.Date;

import fr.formation.inti.dao.EmployeeDaoImpl;
import fr.formation.inti.dao.IEmployeeDao;
import fr.formation.inti.entities.Employee;

public class AppDAO {

	public static void main(String[] args) {

		Employee emp1 = new Employee();
		emp1.setFirstName("test");
		emp1.setLastName("test");
		emp1.setStartDate(new Date());
		
		IEmployeeDao emDao = EmployeeDaoImpl.getInstance();
		
//		int id1 = emDao.saveEmployee(emp1);
//		System.out.println("Id emp1 : " + id1);
		
		//Employee emp2 = emDao.getByID(38);
//		System.out.println(emp2);
//		emDao.updateEmployee(emp2);
		
		//emDao.delete(emp2);
	
		emDao.deleteRangeId(23, 31);
		
		emDao.closeEntityManager();
	}
}
