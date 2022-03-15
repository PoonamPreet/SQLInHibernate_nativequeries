package com.practice.Sql.Hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.practice.Sql.Hibernate.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Configuration config = new Configuration().configure().addAnnotatedClass(Student.class);

		ServiceRegistry reg=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();

		SessionFactory sessionFactory=config.buildSessionFactory(reg);

		Session session =sessionFactory.openSession();

		session.beginTransaction();
		
		//SQLQuery query=session.createSQLQuery("select * from stddetails where marks>60");
		//query.addEntity(Student.class);
		
		SQLQuery query=session.createSQLQuery("select name,marks from StdDetails where id>5");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		
		
		
		
		List students =query.list(); 
		
		for(Object o: students)
		{
			Map m=(Map)o;
			
			System.out.println(m.get("name") +":"+m.get("marks"));
		}
		
		// List<Student> students=query.list();
		 
		 
		// for(Student o:students)
		// {
			// System.out.println(o);
	//	 }
		
		session.getTransaction().commit();
		session.close();
    }
}
