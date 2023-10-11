<%
response.setContentType("application/vnd.ms-excel");
response.setHeader("Content-disposition", "attachment; filename=rolExcel.xls");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table border="1">
	<thead>
		<tr>
			<th>ID</th>
			<th>Nombre</th>
			<th>Correo</th>
			<th>Tipo</th>
			<th>Estado</th>
			<th>Acciones</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="categoria" items="${listaCategoria}">
										<tr>
											<td><c:out value="${categoria.id}" /></td>
											<td><c:out value="${categoria.nombre}" /></td>
											<td><c:out value="${categoria.descripcion}" /></td>
											<td><c:out value="${categoria.estado}" /></td>
											<td>
												<c:if test="${categoria.estado == true }">
												Activo
												</c:if>
												<c:if test="${categoria.estado == false }">
												Inactivo
												</c:if>
											</td>
											<td>
												<center>
													<a
														href="<%=request.getContextPath()%>/Categoria/editar?id=<c:out value='${categoria.id}' />">
														<img src="../img/lapiz.png" width="20px" />
													</a> <a
														href="<%=request.getContextPath()%>/Categoria/eliminar?id=<c:out value='${categoria.id}' />">
														<img src="../img/x.png" width="20px" />
													</a>
												</center>
											</td>
										</tr>
									</c:forEach>
		</tbody>
</table>