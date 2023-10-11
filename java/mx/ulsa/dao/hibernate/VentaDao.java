package mx.ulsa.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mx.ulsa.modelo.Producto;
//import mx.ulsa.modelo.Persona;
import mx.ulsa.modelo.Usuario;
import mx.ulsa.modelo.Venta;
import mx.ulsa.util.HibernateUtil;

public class VentaDao {
		public void saveVenta (Venta venta) {
			Transaction transaction = null;
			try(Session session = HibernateUtil.getSessionFactory().openSession() ){
				transaction = session.beginTransaction();//iniciar transaction
				session.save(venta);//guarda
				transaction.commit();//guarda datos
			}catch(Exception e){
				if(transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
		}

		public void updateVenta(Venta venta) {
			Transaction transaction = null;
			try(Session session = HibernateUtil.getSessionFactory().openSession() ){
				transaction = session.beginTransaction();//iniciar transaction
				session.update(venta);//actualiza
				transaction.commit();//actualiza datos
			}catch(Exception e){
				if(transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
		}

		public void deleteVenta(int id) {
			Transaction transaction = null;
			try(Session session = HibernateUtil.getSessionFactory().openSession() ){
				transaction = session.beginTransaction();//iniciar transaction
				Venta venta =  session.get(Venta.class, id);
				if(venta != null) {
					session.delete(venta);
				}
				transaction.commit();
			}catch(Exception e){
				if(transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
		}

		public Venta getVenta(int id) {
			Transaction transaction = null;
			Venta venta = null;
			try(Session session = HibernateUtil.getSessionFactory().openSession() ){
				transaction = session.beginTransaction();//iniciar transaction
				venta =  session.get(Venta.class, id);
				transaction.commit();
			}catch(Exception e){
				if(transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
			return venta;
		}

		@SuppressWarnings("unchecked")
		public List<Venta> getAllVenta() {
			Transaction transaction = null;
			List<Venta> listaVenta = null;
			try(Session session = HibernateUtil.getSessionFactory().openSession() ){
				transaction = session.beginTransaction();//iniciar transaction
				listaVenta = session.createQuery("from Venta").getResultList();
				transaction.commit();
			}catch(Exception e){
				if(transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
			return listaVenta;
		}
		
		
}
