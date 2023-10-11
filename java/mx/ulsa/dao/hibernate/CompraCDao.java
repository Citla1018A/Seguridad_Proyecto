package mx.ulsa.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mx.ulsa.modelo.CompraC;
import mx.ulsa.modelo.Detalle;
import mx.ulsa.util.HibernateUtil;

public class CompraCDao {

	public void saveCompraC(CompraC compraC) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			session.save(compraC);//guarda
			transaction.commit();//guarda datos
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateCompraC(CompraC compraC) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			session.update(compraC);//actualiza
			transaction.commit();//actualiza datos
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteCompraC(int id) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			CompraC compraC =  session.get(CompraC.class, id);
			if(compraC != null) {
				session.delete(compraC);
			}
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public CompraC getcompraC(int id) {
		Transaction transaction = null;
		CompraC compraC = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			compraC =  session.get(CompraC.class, id);
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return compraC;
	}

	@SuppressWarnings("unchecked")
	public List<CompraC> getAllCompraC() {
		Transaction transaction = null;
		List<CompraC> listaCompraC = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			listaCompraC = session.createQuery("from CompraC").getResultList();
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listaCompraC;
	}
}
