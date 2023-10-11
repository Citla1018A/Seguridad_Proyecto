package mx.ulsa.modelo;

import java.util.Date;

import javax.persistence.Column;

import javax.persistence.Column;
import javax.persistence.Entity;//entidad de la BD
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="producto")

public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")//cambiar a id_producto
	private Integer id;
    
	@Column(name = "curso")//NOMBRE DEL CURSO
	private String curso;
	
	@Column(name = "precio")//Precio del curso
	private double precio;
	
	@Column(name = "estado")
	private Boolean estado;
	
	@Column(name = "ultimo")
	private Date ultimoIngreso;
	
	public Date getUltimoIngreso() {
		return ultimoIngreso;
	}

	public void setUltimoIngreso(Date ultimoIngreso) {
		this.ultimoIngreso = ultimoIngreso;
	}

	@OneToOne
	private Categoria categoria;
	
	

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
