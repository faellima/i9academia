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

	<c:url var="url_1" value="/exercicio/filtro" />

	<form:form action="${url_1}" method='get' modelAttribute="exercicio">
	
		Nome:<form:input path="nome" />

		<input type="submit" value="pesquisar" />
	</form:form>
	<br />

	<br />

	<c:url var="url_2" value="/exercicio/form" />

	<form:form action="${url_2}" method="POST">
		<input type="submit" value="Novo" />
	</form:form>

	<c:if test="${not empty exercicios}">
		<table align="center" width="81%" cellspacing="0" cellpadding="0"
			border="1">
			<tr>
				<th>Nome</th>
				<th>Acao de Edicao</th>
				<th>Acao de Exclusao</th>
			</tr>
			<c:forEach var="exercicio" items="${exercicios}">
				<tr>
					<td>${exercicio.nome}</td>
					<td><c:url var="url_3" value="/exercicio/${exercicio.id}" />
						<form:form action="${url_3}" method="POST">
							<input type="submit" value="Editar" />
						</form:form></td>
					<td><form:form action="${url_3}" method="DELETE">
							<input type="submit" value="Excluir" />
						</form:form></td>

				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${empty exercicios}">
		<c:out value="Não existem exercicios cadastradas" />
	</c:if>


</body>
</html>