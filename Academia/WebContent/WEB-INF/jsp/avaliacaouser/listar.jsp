<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de avalia��es</title>
</head>
<body>

	<c:out value="Listagem de Avaliacoes"></c:out>
	<br>
	<br />
	<c:if test="${not empty avaliacoes}">
		<table align="center" width="81%" cellspacing="0" cellpadding="0"
			border="1">
			<tr>
				<td>Data</td>
				<td>Acao</td>
			</tr>
			<c:forEach var="avaliacao" items="${avaliacoes}">
				<tr>
					<td>${avaliacao.data}</td>
					<td><c:url var="url_2"
							value="/avaliacaouser/detalhar/${avaliacao.id}" /> <form:form
							action="${url_2}" method="POST">
							<input type="submit" value="Detalhar" />
						</form:form></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${empty avaliacoes}">
		<c:out value="N�o existem avaliacoes cadastradas" />
	</c:if>


</body>
</html>