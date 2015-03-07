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

	Editar Matricula do aluno: <c:out default="nome" value="${aluno.nome}"></c:out> <br>
	<br/>

	Modalidades atualmente matriculado:
	
	<c:url var="url_matricular" value="/aluno/salvarmatriculaeditada" />
	<c:url var="url_voltar" value="/aluno/matricular/${aluno.id}" />
	
	<form:form action="${url_matricular}" method='post' modelAttribute="alunomodalidade">
		<input type="hidden" name="id" value="${alunomodalidade.id}"/>
		<input type="hidden" name="aluno.id" value="${aluno.id}"/>
		Modalidade:<form:select path="modalidade.id">
			<form:options items="${modalidades}" />
		</form:select>
		<br/>
		<input type="submit" value="salvar"/>
	</form:form>
		<form action="${url_voltar}" method='post'>
				<input type='submit' value='voltar'>
		</form>

	
	<br/>

</body>
</html>