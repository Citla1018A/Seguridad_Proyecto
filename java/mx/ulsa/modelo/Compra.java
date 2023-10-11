package mx.ulsa.modelo;

import java.util.List;

import javax.persistence.Column;

import javax.persistence.Column;
import javax.persistence.Entity;//entidad de la BD
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class Compra {
	private Usuario usuario;
	private int id_venta;
	private Double pago;
	private String fecha;
	
	private List<CarritoProducto> detalleCompra;
	
	public Compra() {
		
	}

	public Compra(Usuario usuario, int id_venta, Double pago, String fecha, List<CarritoProducto> detalleCompra) {
		this.usuario = usuario;
		this.id_venta = id_venta;
		this.pago = pago;
		this.fecha = fecha;
		this.detalleCompra = detalleCompra;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId_venta() {
		return id_venta;
	}

	public void setId_venta(int id_venta) {
		this.id_venta = id_venta;
	}

	public Double getPago() {
		return pago;
	}

	public void setPago(Double pago) {
		this.pago = pago;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<CarritoProducto> getDetalleCompra() {
		return detalleCompra;
	}

	public void setDetalleCompra(List<CarritoProducto> detalleCompra) {
		this.detalleCompra = detalleCompra;
	}
	
	

	
	
	
	
	
	
}
