/**
 * 
 */
package com.vinay.onetoonemapping.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vinay.onetoonemapping.entity.Course;
import com.vinay.onetoonemapping.entity.Instructor;
import com.vinay.onetoonemapping.entity.InstructorDetail;

/**
 * @author Dell
 *
 */
public class CreateCoursesDemo {

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

			// get the instructor id from the db
			int id = 4;
			Instructor instructor = session.get(Instructor.class, id);
			// create some courses
			Course course1 = new Course("Air Guitar - The Ultimate Guide ");
			Course course2 = new Course("The Pinball Masterclass ");
			// add courses to instructor
			instructor.add(course1);
			instructor.add(course2);
			// save the courses
			session.save(course1);
			session.save(course2);
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done !");
		} finally {
			// add clean up code
			session.close();
			factory.close();
		}

	}

}
