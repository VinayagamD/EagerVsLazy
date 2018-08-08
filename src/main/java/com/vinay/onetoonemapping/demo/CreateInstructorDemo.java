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
public class CreateInstructorDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		try {
			// Create the student object
			System.out.println("Creating 3 student object.... ");
			Instructor instructor = new Instructor("Susan", "Public", "susan.public@gmail.com");
			InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/", "Video Games");
			// associate the objects
			instructor.setInstructorDetail(instructorDetail);
			// start a transaction
			session.beginTransaction();
			// save the instructor
			//
			// Note this will also save the details object
			// because cascade type all
			//
			System.out.println("Saving the instructor: " + instructor);
			session.save(instructor);
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
