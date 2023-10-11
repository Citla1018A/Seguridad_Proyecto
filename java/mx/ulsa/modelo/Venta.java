package mx.ulsa.modelo;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToOne;

@Entity
@Table(name = "venta")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venta")
    private Integer id_venta;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@Column(name = "productos")
	private ArrayList<String> productos;
	
	@Column(name = "totalVenta")
	private Double totalVenta;
	
	
	@Column(name = "registro")
	private Date registroIngreso;
	
	

	public Venta(Usuario usuario, ArrayList<String> productos, Double totalVenta,
			Date registroIngreso) {
		this.id_venta = id_venta;
		this.usuario = usuario;
		this.productos = productos;
		this.totalVenta = totalVenta;
		this.registroIngreso = registroIngreso;
	}



	public Integer getId_venta() {
		return id_venta;
	}

	public void setId_venta(Integer id_venta) {
		this.id_venta = id_venta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<String> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<String> productos) {
		this.productos = productos;
	}

	public Double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}

/*	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}*/

	public Date getRegistroIngreso() {
		return registroIngreso;
	}

	public void setRegistroIngreso(Date registroIngreso) {
		this.registroIngreso = registroIngreso;
	}
	
	
    
	
	
	
	
	
}
