package mx.ulsa.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

//import mx.ulsa.modelo.Persona;
import mx.ulsa.modelo.Usuario;
import mx.ulsa.util.HibernateUtil;

public class UsuarioDao {
	public void saveUsuario(Usuario usuario) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			session.save(usuario);//guarda
			transaction.commit();//guarda datos
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateUsuario(Usuario usuario) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			session.update(usuario);//actualiza
			transaction.commit();//actualiza datos
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteUsuario(int id) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			Usuario usuario =  session.get(Usuario.class, id);
			if(usuario != null) {
				session.delete(usuario);
			}
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Usuario getUsuario(int id) {
		Transaction transaction = null;
		Usuario usuario = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			 usuario =  session.get(Usuario.class, id);
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return usuario;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getAllUsuario() {
		Transaction transaction = null;
		List<Usuario> listaRol = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ){
			transaction = session.beginTransaction();//iniciar transaction
			listaRol = session.createQuery("from Usuario").getResultList();
			transaction.commit();
		}catch(Exception e){
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listaRol;
	}
	
	public Usuario getUsuarioByCorreo(String correo) {
		Transaction transaction = null;
        Usuario usuario = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            usuario = (Usuario)session.createQuery("from Usuario where correo = :param_correo").
            		setParameter("param_correo", correo).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return usuario;
	}

	public Usuario getUsuarioByCorreoAndPass(String correo, String pwd) {
		Transaction transaction = null;
        Usuario usuario = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            usuario = (Usuario)session.
            		createQuery("from Usuario where correo = :param_correo and pass = :param_pwd").
            		setParameter("param_correo", correo).
            		setParameter("param_pwd", pwd).
            		uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return usuario;
	}
	
	
}
