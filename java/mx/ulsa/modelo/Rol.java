package mx.ulsa.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;//entidad de la BD
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rol")
public class Rol {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) //auto incrementable
	@Column(name="id")
	private Integer id;//id de la llave primaria
	
	@Column(name = "nombre_rol")
	private String nombre_rol;
	
	public Rol() {
		
	}
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "estado")
	private Boolean estado;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre_rol() {
		return nombre_rol;
	}

	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Rol(Integer id, String nombre_rol, String descripcion, Boolean estado) {
		super();
		this.id = id;
		this.nombre_rol = nombre_rol;
		this.descripcion = descripcion;
		this.estado = estado;
	}
	
	public Rol( String nombre_rol, String descripcion, Boolean estado) {
		super();
		this.nombre_rol = nombre_rol;
		this.descripcion = descripcion;
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Rol [id=" + id + ", nombre_rol=" + nombre_rol + ", descripcion=" + descripcion + ", estado=" + estado
				+ "]";
	}
	
	
	
}
