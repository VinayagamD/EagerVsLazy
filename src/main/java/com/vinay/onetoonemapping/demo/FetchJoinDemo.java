/**
 * 
 */
package com.vinay.onetoonemapping.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.vinay.onetoonemapping.entity.Course;
import com.vinay.onetoonemapping.entity.Instructor;
import com.vinay.onetoonemapping.entity.InstructorDetail;

/**
 * @author Dell
 *
 */
public class FetchJoinDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		try {
			// Create the student object
			System.out.println("Creating 3 student object.... ");
			// start a transaction
			session.beginTransaction();
			
//			option 2: Hibernate query with HQL
			

			// get the instructor id from the db
			int id = 4;
			Query<Instructor> query = session.createQuery("select i FROM Instructor i "
					+ " JOIN FETCH i.courses "
					+ " WHERE i.id = :theInstructorId ", 
					Instructor.class);
			
//			Set the query parameter
			query.setParameter("theInstructorId", id);
			
//			execute the query and get instructor
			Instructor instructor = query.getSingleResult();
			System.out.println("Courses : "+ instructor.getCourses());
//			close the session
			session.close();
			System.out.println("The sessions is now closed");
			//			Since courses are lazy loaded ... this should fail
			// get the course for the instructor
			System.out.println("Courses : "+ instructor.getCourses());
//			// commit the transaction
//			session.getTransaction().commit();
			System.out.println("Done !");
		} finally {
			// add clean up code
			session.close();
			factory.close();
		}

	}

}
