package mx.ulsa.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Column;
import javax.persistence.Entity;//entidad de la BD
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="compraC")
public class CompraC {
/*	private Usuario usuario;
	private Producto producto;
	private List<Compra> listaCompra;*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_compra")
	private Integer id_compra;
	
	@Column(name = "cantidad")
	private String cantidad;
	
	@Column(name = "total")
	private String total;

	public CompraC(Integer id_compra, String cantidad, String total) {
	//	super();
		this.id_compra = id_compra;
		this.cantidad = cantidad;
		this.total = total;
	}

	public CompraC(ArrayList<Carrito> listaCarrito) {
		
	}

	public Integer getId_compra() {
		return id_compra;
	}

	public void setId_compra(Integer id_compra) {
		this.id_compra = id_compra;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	

	
	
	
	
	
	
	
	
}
