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
import com.vinay.onetoonemapping.entity.Student;

/**
 * @author Dell
 *
 */
public class DeleteMaryStudentDemo {

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
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		try {
			// Create the student object
			System.out.println("Creating 3 student object.... ");
			// start a transaction
			session.beginTransaction();

			// get the student from the database
			int studentId =2;
			Student student = session.get(Student.class,studentId);
			
			
			
			
			System.out.println("\nLoaded student : "+student);
			System.out.println("Courses: " + student.getCourses());
			
//			delete the student
			System.out.println("\nDeleting the Student "+student);
			session.delete(student);

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
