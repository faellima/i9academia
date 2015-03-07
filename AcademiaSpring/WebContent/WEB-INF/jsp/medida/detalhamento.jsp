<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de alunos</title>
</head>
<body>
	
	
	<c:if test="${not empty medidas}">
		<table>
			<tr>
				<th>Medida</th>
				<th>Valor</th>
			</tr>
			<c:forEach var="medida" items="${medidas}">
			<tr>
				<td>${medida.nome}</td>
				<td>${medida.valor}</td>					
			</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${empty alunos}">
		<c:out value="Não existem alunos cadastradas" />
	</c:if>

	<c:url var="url" value="/aluno" />
	
	<form:form action="${url}" method="POST">
		<input type="submit" value="voltar"/>
	</form:form>

</body>
</html>