package mx.ulsa.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mx.ulsa.modelo.Persona;
import mx.ulsa.util.HibernateUtil;

public class PersonaDao {
	public void savePersona(Persona persona) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			session.save(persona);//guarda
			transaction.commit();//guarda datos
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updatePersona(Persona persona) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			session.update(persona);//actualiza
			transaction.commit();//actualiza datos
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deletePersona(int id) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			Persona persona =  session.get(Persona.class, id);
			if(persona != null) {
				session.delete(persona);
			}
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Persona getPersona(int id) {
		Transaction transaction = null;
		Persona persona = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			 persona =  session.get(Persona.class, id);
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return persona;
	}

	@SuppressWarnings("unchecked")
	public List<Persona> getAllPersona() {
		Transaction transaction = null;
		List<Persona> listaRol = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			listaRol = session.createQuery("from Persona").getResultList();
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listaRol;
	}
}
