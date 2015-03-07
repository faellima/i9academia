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
	
	<c:url var="url_1" value="/avaliacao/form" />
	
	<c:url var="url_filtro" value="/avaliacao/filtro" />
	
	<form:form action="${url_filtro}" method='get' modelAttribute="aluno">
	
		Aluno:<form:input path="nome"/>

		<input type="submit" value="pesquisar"/>
	</form:form>
	<br/>
	
	<form:form action="${url_1}" method="POST">
		<input type="submit" value="Novo"/>
	</form:form>
	
	<c:if test="${not empty avaliacoes}">
		<table align="center" width="81%" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<td>Data</td>
				<td>Aluno</td>
				<td>Acao de Edicao</td>
				<td>Acao de Exclusao</td>
			</tr>
			<c:forEach var="avaliacao" items="${avaliacoes}">
			<tr>
				<td>${avaliacao.data}</td>
				<td>${avaliacao.aluno.nome}</td>
				<td>  
				     <c:url var="url_2" value="/avaliacao/${avaliacao.id}" />
				     <form:form action="${url_2}" method="POST">
						<input type="submit" value="Editar"/>
					</form:form></td>
					<td><form:form action="${url_2}" method="DELETE">
						<input type="submit" value="Excluir"/>
					</form:form></td>
				</td>
			</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${empty avaliacoes}">
		<c:out value="Não existem avaliacoes cadastradas" />
	</c:if>


</body>
</html>