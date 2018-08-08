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
public class DeleteDemo {

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
			
//			get the instructor by primary key / id
			int theId = 1;
			
			Instructor instructor= session.get(Instructor.class, theId);
			System.out.println("Found instructor : " +instructor);

//			delete the  instructors
			if(instructor != null) {
				System.out.println("Deleting : "+instructor);
//				Note will also delete associated "details" object
//				because cascade type all
//				
				
				session.delete(instructor);
			}
				
//			commit the transaction
			session.getTransaction().commit();
			System.out.println("Done !");
		}finally {
			factory.close();
		}
	}

}
