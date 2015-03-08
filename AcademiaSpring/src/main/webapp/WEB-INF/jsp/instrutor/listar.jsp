<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de instrutores</title>
</head>
<body>
	
	<c:url var="url_1" value="instrutor/form"/>
	
	<c:url var="url_filtro" value="/instrutor/filtro" />

		<form:form action="${url_filtro}" method='get' modelAttribute="ficha">
	
		Instrutor:<form:input path="instrutor.nome"/>

		Modalidade:<form:select path="alunoModalidade.modalidade.id">
			  <form:option value="">TODOS</form:option>
			<form:options items="${modalidades}" />
		</form:select>
		
		<input type="submit" value="pesquisar"/>
	</form:form>
	
	<br/>
	
	<c:if test="${not empty instrutores}">
		<table align="center" width="81%" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<th>Nome</th>
				<th>Cpf</th>
				<th>Tel. Fixo</th>
				<th>Celular</th>
				<th>Email</th>
				<th>Acao de Edicao</th>
				<th>Acao de Exclusao</th>
			</tr>
			<c:forEach var="instrutor" items="${instrutores}">
			<tr>
				<td>${instrutor.nome}</td>
				<td>${instrutor.cpf}</td>
				<td>${instrutor.telFixo}</td>
				<td>${instrutor.telCelular}</td>
				<td>${instrutor.email}</td>
				<td>  
				    <c:url var="url_2" value="/instrutor/${instrutor.id}" />
				    <form:form action="${url_2}" method="POST">
						<input type="submit" value="Editar"/>
					</form:form></td>
				
					<td><form:form action="${url_2}" method="DELETE">
						<input type="submit" value="Excluir"/>
					</form:form></td>
				
			</tr>
			</c:forEach>
		</table>
	</c:if>

	<form:form action="${url_1}" method="POST">
		<input type="submit" value="Novo"/>
	</form:form>

	<c:if test="${empty instrutores}">
		<c:out value="Não existem instrutores cadastradas" />
	</c:if>


</body>
</html>