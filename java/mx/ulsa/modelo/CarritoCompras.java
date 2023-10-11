package mx.ulsa.modelo;

import java.util.ArrayList;
import java.util.List;

public class CarritoCompras {
	private Usuario usuario;
	private List<CarritoProducto> listaCarritoProducto;
	private Double compraSubTotal;
	private Double compraIva;
	private Double compraTotal;
	
	public CarritoCompras() {
		
	}

	public CarritoCompras(Usuario usuario, List<CarritoProducto> listaCarritoProducto) {
		super();
		this.usuario = usuario;
		this.listaCarritoProducto = listaCarritoProducto;
	}

	public CarritoCompras(Usuario usuario, List<CarritoProducto> listaCarritoProducto, Double compraSubTotal,
			Double compraIva, Double compraTotal) {
		super();
		this.usuario = usuario;
		this.listaCarritoProducto = listaCarritoProducto;
		this.compraSubTotal = compraSubTotal;
		this.compraIva = compraIva;
		this.compraTotal = compraTotal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<CarritoProducto> getListaCarritoProducto() {
		return listaCarritoProducto;
	}

	public void setListaCarritoProducto(List<CarritoProducto> arrayList) {
		this.listaCarritoProducto = arrayList;
	}

	public Double getCompraSubTotal() {
		return compraSubTotal;
	}

	public void setCompraSubTotal(Double compraSubTotal) {
		this.compraSubTotal = compraSubTotal;
	}

	public Double getCompraIva() {
		return compraIva;
	}

	public void setCompraIva(Double compraIva) {
		this.compraIva = compraIva;
	}

	public Double getCompraTotal() {
		return compraTotal;
	}

	public void setCompraTotal(Double compraTotal) {
		this.compraTotal = compraTotal;
	}

	public void setListaCarritoProducto(ArrayList<Producto> arrayList) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
