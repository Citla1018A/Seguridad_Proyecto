package mx.ulsa.modelo;

public class Carrito {

	private Integer cantidad;
//	private Double precio;
	private Double total;
	private Producto producto;
	private Double iva;
	
	public Carrito() {
		
	}
	
	public Carrito(Integer cantidad,Double total, Producto producto, Double iva) {
		this.cantidad=cantidad;
		this.total=total;
		this.producto=producto;
		this.iva=iva;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
}
