package mx.ulsa.util;

//import java.lang.module.Configuration;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import mx.ulsa.modelo.Carrito;
import mx.ulsa.modelo.CarritoCompras;
import mx.ulsa.modelo.Categoria;
import mx.ulsa.modelo.CompraC;
import mx.ulsa.modelo.Persona;
import mx.ulsa.modelo.Producto;
import mx.ulsa.modelo.Rol;
import mx.ulsa.modelo.Usuario;
import mx.ulsa.modelo.Venta;


//import com.sun.xml.fastinfoset.sax.Properties;


public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				// Configuracion de Hibernate equivalente a hibernate.cfg.xml
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "org.postgresql.Driver");
				settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/bd_seg");
				settings.put(Environment.USER, "administrador");
				settings.put(Environment.PASS, "administrador");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

				settings.put(Environment.SHOW_SQL, "true");

				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				settings.put(Environment.HBM2DDL_AUTO, "update"); /* create-drop */

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(Rol.class);
				configuration.addAnnotatedClass(Persona.class);
				configuration.addAnnotatedClass(Usuario.class);
				configuration.addAnnotatedClass(Categoria.class);
				configuration.addAnnotatedClass(Producto.class);
				configuration.addAnnotatedClass(Venta.class);
				
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				System.out.println("Hibernate Java Config serviceRegistry creado");
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
