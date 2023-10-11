package mx.ulsa.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mx.ulsa.modelo.Categoria;
import mx.ulsa.util.HibernateUtil;

public class CategoriaDao {
	
	public void saveCategoria(Categoria categoria) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			session.save(categoria);//guarda
			transaction.commit();//guarda datos
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateCategoria(Categoria categoria) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			session.update(categoria);//actualiza
			transaction.commit();//actualiza datos
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteCategoria(int id) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			Categoria categoria =  session.get(Categoria.class, id);
			if(categoria != null) {
				session.delete(categoria);
			}
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Categoria getCategoria(int id) {
		Transaction transaction = null;
		Categoria categoria = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			categoria =  session.get(Categoria.class, id);
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return categoria;
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> getAllCategoria() {
		Transaction transaction = null;
		List<Categoria> listaCategoria = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			listaCategoria = session.createQuery("from Categoria").getResultList();
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listaCategoria;
	}
}
