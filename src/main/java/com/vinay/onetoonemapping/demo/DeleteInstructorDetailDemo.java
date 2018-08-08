/**
 * 
 */
package com.vinay.onetoonemapping.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vinay.onetoonemapping.entity.Instructor;
import com.vinay.onetoonemapping.entity.InstructorDetail;
import com.vinay.onetoonemapping.entity.Student;


/**
 * @author Dell
 *
 */
public class DeleteInstructorDetailDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//			create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
//		create session
		Session session = factory.getCurrentSession();
		try {
//			Create the student object
			System.out.println("Creating 3 student object.... ");
			
//			start a transaction
			session.beginTransaction();
			
// 			get the instruction detail object
			int id = 3;
			InstructorDetail instructorDetail =  session.get(InstructorDetail.class, id);
//			print instruction detail
			System.out.println(" Instructor Details :"+instructorDetail);
//			print the associate instructor
			System.out.println("Associated Instructor :"+instructorDetail.getInstructor());
//			now let's delete the instructor detail
			System.out.println("Deleting the instructor detail: " + instructorDetail);
			instructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(instructorDetail);
			
//			commit the transaction
			session.getTransaction().commit();
			System.out.println("Done !");
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
//			handle the connection leak issue
			session.close();
			factory.close();
		}
	}

}
