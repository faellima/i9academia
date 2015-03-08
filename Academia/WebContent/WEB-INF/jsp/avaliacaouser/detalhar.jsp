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

	<c:out value="Destalhamento da avaliacao de ${aluno.nome}"></c:out>
	<br>
	<br />
	<c:if test="${not empty medidaAvaliacoes}">
		<table align="center" width="81%" cellspacing="0" cellpadding="0"
			border="1">
			<tr>
				<td>Medida</td>
				<td>Valor</td>
			</tr>
			<c:forEach var="medidaAvaliacao" items="${medidaAvaliacoes}">
				<tr>
					<td>${medidaAvaliacao.medicao.nome}</td>
					<td>${medidaAvaliacao.valor}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${empty medidaAvaliacoes}">
		<c:out value="Não existem avaliacoes cadastradas" />
	</c:if>

	<c:url var="url_voltar" value="/avaliacaouser" />
	<form action="${url_voltar}" method='get'>
		<input type='submit' value='voltar'>
	</form>


</body>
</html>