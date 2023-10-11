package mx.ulsa.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mx.ulsa.modelo.Carrito;
import mx.ulsa.modelo.Persona;
import mx.ulsa.util.HibernateUtil;

public class CarritoComprasDao {
	public void saveCarritoCompras(ArrayList<Carrito> listaCarrito) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			session.save(listaCarrito);//guarda
			transaction.commit();//guarda datos
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateCarritoCompras(Carrito carrito) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			session.update(carrito);//actualiza
			transaction.commit();//actualiza datos
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteCarritoCompras(int id) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			Carrito carrito =  session.get(Carrito.class, id);
			if(carrito != null) {
				session.delete(carrito);
			}
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Carrito getCarritoCompras(int id) {
		Transaction transaction = null;
		Carrito carrito = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			carrito =  session.get(Carrito.class, id);
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return carrito;
	}

	@SuppressWarnings("unchecked")
	public List<Carrito> getAllCarritoCompras() {
		Transaction transaction = null;
		List<Carrito> listaRol = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			listaRol = session.createQuery("from CarritoCompras").getResultList();
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
