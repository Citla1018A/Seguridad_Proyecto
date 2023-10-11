<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="mx.ulsa.modelo.Usuario"%>
<%@page import="mx.ulsa.modelo.Producto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mis Compras</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
	integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
	crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
		<div class="container">
			<a class="navbar-brand"
				href="<%=request.getContextPath()%>/Index.jsp"> <img
				src="https://img.icons8.com/ios-filled/50/000000/circled-left-2.png" />
				Regresar
			</a> <a class="navbar-brand"
				href="<%=request.getContextPath()%>/Navegacion/producto"> <img
				src="https://img.icons8.com/ios-filled/50/000000/computer.png" />
				Cursos
			</a> <a class="navbar-brand"
				href="<%=request.getContextPath()%>/usuario/Miperfil.jsp"> <c:if
					test="${usuario !=null }">
				${ usuario.getPersona().getNombre ()}
				</c:if> <img
				src="https://img.icons8.com/external-bearicons-glyph-bearicons/64/000000/external-User-essential-collection-bearicons-glyph-bearicons.png" />
			</a> <a class="navbar-brand"
				href="<%=request.getContextPath()%>/Usuario/cerrarSesion"
				class="btn btn-primary">Salir</a>
	</nav>
	<h1 align="center">Mis Compras</h1>
	<div
		class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
		<h6 class="m-0 font-weight-bold text-primary">Mis compras</h6>
		<a href="<%=request.getContextPath()%>/CarritoCompra/excel"><img src="https://img.icons8.com/color/48/000000/ms-excel.png"/></a>
	</div>
	<div id="imprimir">
	<div class="table-responsive p-3">
		<table class="table align-items-center table-flush table-hover"
			id="dataTableHover">
			<thead class="thead-light">
				<tr>
					<th>Nombre del curso</th>
					<th>Precio</th>
					<th>Cantidad</th>
				</tr>
			</thead>
			<c:forEach var="carrito" items="${listarcarrito}">
				<tr>
					<th>${carrito.getProducto().getCurso()}</th>
					<th>${carrito.getProducto().getPrecio()}</th>
					<th>${carrito.getCantidad()}</th>
				</tr>
			</c:forEach>
		</table>
	</div>
	</div>
	
	<div style="margin: 40px;">
		<div id="liveAlertPlaceholder"></div>
		<button type="button" class="btn btn-info" id="liveAlertBtn"
			onclick="imprimirElemento()">Generar Ticket</button>
	</div> 
	<div style="margin: 40px;">
	<a href="" class="btn btn-warning btn-block" data-toggle="modal"
								data-target="#exampleModal"><h4>Pagar</h4></a>

							<!-- Modal -->
							<div style="margin: 80px;">
							<div class="modal fade" id="exampleModal" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Pagar</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<h5>Tarjeta de crédito o débito</h5>
											<img
												src="https://img.icons8.com/color/48/000000/mastercard.png" />
											<img src="https://img.icons8.com/color/48/000000/visa.png" />
											<img src="https://img.icons8.com/fluency/48/000000/amex.png" />
											<div class="form-group owner">
											<form>
											<div class="form-group owner">
													<label for="owner">Nombre de la tarjeta *</label> <input type="text"
														class="form-control" id="owner" required="required" >
												</div>
												<div class="form-group" id="card-number-field">
													<label for="cardNumber">Número de tarjeta *</label> <input
														type="text" class="form-control" id="cardNumber" required="required">
												</div>
												<div class="form-group CVV">
													<label for="cvv">CVV *</label> <input type="text"
														class="form-control" id="cvv" required="required">
												</div>
												<div class="form-group" id="expiration-date">
													<label>Fecha de vencimiento *</label> <select required="required">
														<option value="01">Enero</option>
														<option value="02">Febrero</option>
														<option value="03">Marzo</option>
														<option value="04">Abril</option>
														<option value="05">Mayo</option>
														<option value="06">Junio</option>
														<option value="07">Julio</option>
														<option value="08">Agosto</option>
														<option value="09">Septiembre</option>
														<option value="10">Octubre</option>
														<option value="11">Noviembre</option>
														<option value="12">Deciembre</option>
													</select> <select>
														<option value="16">2021</option>
														<option value="17">2022</option>
														<option value="18">2023</option>
														<option value="19">2024</option>
														<option value="20">2025</option>
														<option value="21">2026</option>
													</select>
												</div>	
											</form>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">Cancelar</button>
											<button type="button" class="btn btn-info">Realizar pago</button>
											
										</div>
									</div>
							
								</div>
							</div>
						</div>
						</div>
						</div>
	<div style="margin: 80px;">
		<ul class="nav justify-content-end">
			<li class="nav-item"><a class="nav-link active"
				aria-current="page" href="#"><h2>Total:$ ${total}</h2></a></li>

		</ul>
	</div>
</body>

<script>
function imprimirElemento() {
var elemento = document.getElementById("imprimir");
var ventana = window.open('', 'PRINT', 'height=400,width=600');
ventana.document.write('<html><head><title>Ticket</title>');
ventana.document.write('</head><body >');
ventana.document.write(elemento.innerHTML);
ventana.document.write('</body></html>');
ventana.document.close();
ventana.focus();
//ventana.onload = function() {
ventana.print();
ventana.close();
//};
return true;
}

</script>


</html>