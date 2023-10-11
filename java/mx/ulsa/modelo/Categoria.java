package mx.ulsa.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;//entidad de la BD
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categoria")
public class Categoria {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) //auto incrementable
	@Column(name="id")//cambiar a id_categoria
	private Integer id;//id de la llave primaria
	
	@Column(name = "nombre")
	private String nombre;
	
	public Categoria() {
		
	}
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "estado")
	private Boolean estado;
	
	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre= nombre;
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

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Categoria(Integer id, String nombre, String descripcion, Boolean estado) {
		super();
		this.id = id;
		this.nombre= nombre;
		this.descripcion = descripcion;
		this.estado = estado;
	}
	
	public Categoria( String nombre, String descripcion, Boolean estado) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", estado=" + estado + "]";
	}
	
	
}
