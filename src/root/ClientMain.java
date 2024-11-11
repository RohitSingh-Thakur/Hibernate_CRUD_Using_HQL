package root;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientMain {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure().addAnnotatedClass(Employee.class);
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {

			// Create Employee Objects

			 Employee emp1 = new Employee("Rohit Singh", "Pune", "IT", 90.000, "male",
			 "9673428105");
			 Employee emp2 = new Employee("Mayuri Raut", "Mumbai", "HR", 50.000, "female",
			 "6473826457");
			 Employee emp3 = new Employee("Pankaj Chauhan", "Nagpur", "Sales", 20.000,
			 "male", "6574543456");
			 Employee emp4 = new Employee("Jay Galani", "Dhule", "Marketing", 60.000,
			 "male", "2345678901");
			 Employee emp5 = new Employee("Kishor Sinha", "Kolkata", "Manager", 80.000,
			 "male", "654253685");

			 //Create (Insert) Hibernate automatically generates the insert statement
			 session.save(emp1);
			 session.save(emp2);
			 session.save(emp3);
			 session.save(emp4);
			 session.save(emp5);

			 transaction.commit();

			// READ(Select) To retrieve data, you use from and specify the entity class. we can use where to filter results.
			 Query query1 = session.createQuery("FROM Employee");
			 List<Employee> list1 = query1.list();
			 for (Employee employee : list1) {
			 System.out.println(employee);
			 }

			Query query2 = session.createQuery("FROM Employee where salary > :NUMBER");
			query2.setParameter("NUMBER", 80.000);
			List<Employee> list2 = query2.list();
			for (Employee employee : list2) {
				System.out.println(employee);
			}

			Query query3 = session.createQuery("FROM Employee where salary >= :NUMBER");
			query3.setParameter("NUMBER", 80.000);
			List<Employee> list3 = query3.list();
			for (Employee employee : list3) {
				System.out.println(employee);
			}
			
			Query query4 = session.createQuery("FROM Employee where salary > 80.000");
			List<Employee> list4 = query4.list();
			for (Employee employee : list4) {
				System.out.println(employee);
			}
			
			// Update use update statement, followed by the entity class and the set conditions.

			 Query query5 = session.createQuery("update Employee set salary = :SAL where empId = :EMPID");
			 query5.setParameter("SAL", 111.111);
			 query5.setParameter("EMPID", 2);

			 query5.executeUpdate();
			 transaction.commit();

			// Delete
			 Query query6 = session.createQuery("delete from Employee where empId= :EMPID"); 
			 // Space is not allowed after parameter prefix ':' [delete from Employee where empId= : EMPID]
			 query6.setParameter("EMPID", 4);
			 query6.executeUpdate();
			 transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			sessionFactory.close();
		} finally {
			session.close();
			sessionFactory.close();
		}

	}
}
