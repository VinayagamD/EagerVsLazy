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
public class AddCoursesForMaryDemo {

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

			// get the instructor id from the db
			
			// create some courses
			Course course = new Course("Pacman - How To Score One Million Points");
			
			// add some reviews
			
			
//			save the course ... and leverage the cascade all :-)
			System.out.println("Saving the course ");
			System.out.println(course);
			session.save(course);
			System.out.println("Saved the course " + course);
			
//			create the student
			Student student1 = new Student("vinay","ganesh","vinayganeshdxj@gmail.com");
			Student student2 = new Student("mary","public","mary@gmail.com");
//			add the student to the course
			course.addStudent(student1);
			course.addStudent(student2);
			
//			save the students
			System.out.println("\n Saving the students ... ");
			session.save(student1);
			session.save(student2);
			
			System.out.println("Saved students : "+course.getStudents());

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
