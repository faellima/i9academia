<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de modalidades</title>
</head>
<body>

	<font color="#FF0000"><c:out value="${matriculaError}"></c:out></font>
	<br/>

	Matricular aluno: <c:out default="nome" value="${aluno.nome}"></c:out> <br>
	<br/>
	
	Modalidades atualmente matriculado:
	
	<c:url var="url_matricular" value="/aluno/efetuarmatricula" />
	<c:url var="url_voltar" value="/aluno" />
	
	<c:if test="${not empty modalidadesDoAluno}">
		<table align="center" width="81%" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<th>Nome</th>
				<th>Acao de Edicao</th>
				<th>Acao de Exclusao</th>
			</tr>
			<c:forEach var="modalidadeDoAluno" items="${modalidadesDoAluno}">
			<tr>
				<td>${modalidadeDoAluno.modalidade.nome}</td>
				<td>  
				     <c:url var="url_editar" value="/aluno/editarmatricula/${modalidadeDoAluno.id}" />
				     <form:form action="${url_editar}" method="POST">
						<input type="submit" value="Editar"/>
					</form:form></td>
					<c:url var="url_remover" value="/aluno/removermatricula/${modalidadeDoAluno.id}" />
					<td><form:form action="${url_remover}" method="DELETE">
						<input type="submit" value="Excluir"/>
					</form:form>
					
				</td>
			</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${empty modalidadesDoAluno}">
		<c:out value="O aluno nao esta matriculado" />
	</c:if>
	<br/>
	
	<form:form action="${url_matricular}" method='get' modelAttribute="alunomodalidade">
		<input type="hidden" name="aluno.id" value="${aluno.id}"/>
		Modalidade:<form:select path="modalidade.id">
			  <form:option value="">Modalidades</form:option>
			<form:options items="${modalidades}" />
		</form:select>
		<br/>
		<input type="submit" value="salvar"/>
	</form:form>

	<form action="${url_voltar}" method='get'>
			<input type='submit' value='voltar'>
	</form>
	
	
	<br/>

</body>
</html>