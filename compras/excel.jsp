<%
response.setContentType("application/vnd.ms-excel");
response.setHeader("Content-disposition", "attachment; filename=compraExcel.xls");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table border="1">
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