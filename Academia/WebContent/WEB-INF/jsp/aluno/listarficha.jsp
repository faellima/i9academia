<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de avaliações</title>
</head>
<body>

	<c:if test="${not empty fichas}">
		<table>
			<tr>
				<th>Data</th>
				<th>Ações</th>
			</tr>
			<c:forEach var="ficha" items="${fichas}">
				<tr>
					<td>${ficha.data}</td>
					<td><c:url var="url" value="/ficha/${ficha.id}" /> <form:form
							action="${url}" method="POST">
							<input type="submit" value="Editar" />
						</form:form></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${empty fichas}">
		<c:out value="Não existem fichas cadastradas" />
	</c:if>


</body>
</html>