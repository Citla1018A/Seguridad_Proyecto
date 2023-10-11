package mx.ulsa.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mx.ulsa.modelo.Rol;
import mx.ulsa.util.HibernateUtil;

public class RolDao {

	public void saveRol(Rol rol) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			session.save(rol);//guarda
			transaction.commit();//guarda datos
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateRol(Rol rol) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			session.update(rol);//actualiza
			transaction.commit();//actualiza datos
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteRol(int id) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			Rol rol =  session.get(Rol.class, id);
			if(rol != null) {
				session.delete(rol);
			}
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Rol getRol(int id) {
		Transaction transaction = null;
		Rol rol = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			 rol =  session.get(Rol.class, id);
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return rol;
	}

	@SuppressWarnings("unchecked")
	public List<Rol> getAllRol() {
		Transaction transaction = null;
		List<Rol> listaRol = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			listaRol = session.createQuery("from Rol").getResultList();
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
