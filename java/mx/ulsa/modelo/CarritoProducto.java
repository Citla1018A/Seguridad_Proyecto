package mx.ulsa.modelo;

public class CarritoProducto {
	private Integer id;//id curso
	private String nombre;
	private Double descripcion;
	private Integer cantidad_solicitada;
	private Double precio;
	private Double total_a_pagar;
	
	public CarritoProducto() {
		
	}

	public CarritoProducto(Integer id, String nombre, Double descripcion, Integer cantidad_solicitada, Double precio,
			Double total_a_pagar) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad_solicitada = cantidad_solicitada;
		this.precio = precio;
		this.total_a_pagar = total_a_pagar;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(Double descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCantidad_solicitada() {
		return cantidad_solicitada;
	}

	public void setCantidad_solicitada(Integer cantidad_solicitada) {
		this.cantidad_solicitada = cantidad_solicitada;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getTotal_a_pagar() {
		return total_a_pagar;
	}

	public void setTotal_a_pagar(Double total_a_pagar) {
		this.total_a_pagar = total_a_pagar;
	}
	
	
	
	
}
