<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de medidas</title>
</head>
<body>

	<c:url var="url_1" value="/medida/filtro" />
	<form:form action="${url_1}" method="GET" modelAttribute="medicao">
		Nome:<form:input path="nome" />
		<input type="submit" value="pesquisar" />
	</form:form>

	<br>

	<c:url var="url_2" value="/medida/form" />

	<form:form action="${url_2}" method="POST">
		<input type="submit" value="Novo" />
	</form:form>

	<c:if test="${not empty medidas}">
		<table align="center" width="81%" cellspacing="0" cellpadding="0"
			border="1">
			<tr>
				<th>Nome</th>
				<th>Acao de Edicao</th>
				<th>Acao de Exclusao</th>
			</tr>
			<c:forEach var="medida" items="${medidas}">
				<tr>
					<td>${medida.nome}</td>
					<td><c:url var="url_3" value="/medida/${medida.id}" /> <form:form
							action="${url_3}" method="POST">
							<input type="submit" value="Editar" />
						</form:form></td>
					<td><form:form action="${url_3}" method="DELETE">
							<input type="submit" value="Excluir" />
						</form:form></td>

					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${empty medidas}">
		<c:out value="Não existem medidas cadastradas" />
	</c:if>


</body>
</html>