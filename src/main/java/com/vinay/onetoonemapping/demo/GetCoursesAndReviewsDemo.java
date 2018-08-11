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
import com.vinay.onetoonemapping.entity.Review;

/**
 * @author Dell
 *
 */
public class GetCoursesAndReviewsDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		try {
			// Create the student object
			System.out.println("Creating 3 student object.... ");
			// start a transaction
			session.beginTransaction();

			// get the instructor id from the db
			
			// create some courses
			Course course = new Course("Pacman - How To Score One Million Points");
			
			// add some reviews
			course.addReview(new Review("Great course ... love it! "));
			course.addReview(new Review("Cool course ... job well done"));
			course.addReview(new Review("What a dummp course, you are an idiot! "));
			
//			save the course ... and leverage the cascade all :-)
			System.out.println("Saving the course ");
			System.out.println(course);
			System.out.println(course.getReviews());
			session.save(course);
			
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
