/**
 * 
 */
package com.vinay.onetoonemapping.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vinay.onetoonemapping.entity.Instructor;
import com.vinay.onetoonemapping.entity.InstructorDetail;


/**
 * @author Dell
 *
 */
public class CreateDemo {

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
			Instructor instructor = new Instructor("Vinay","Ganesh","vinayganeshdxj@gmail.com");
			InstructorDetail instructorDetail =  new InstructorDetail("https://www.youtube.com/channel/UC79MQ-dYggdGQYur4A4V7uQ?view_as=subscriber"
					,"Explore new Technology And Inventions");
//			associate the objects
			instructor.setInstructorDetail(instructorDetail);
//			start a transaction
			session.beginTransaction();
//			save the instructor
//			
//			Note this will also save the details object
//			because cascade type all
//			
			System.out.println("Saving the instructor: "+instructor);
			session.save(instructor);
//			commit the transaction
			session.getTransaction().commit();
			System.out.println("Done !");
		}finally {
			factory.close();
		}
	}

}
