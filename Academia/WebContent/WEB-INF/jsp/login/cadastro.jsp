
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Administrador</title>
</head>
<body>

	<c:url var="url" value="/login/criarAdministrador" />
	<c:url var="url_1" value="/login/voltar" />

	<form:form action="${url}" method='post' modelAttribute="administrador">
			Login:<form:input path="usuario.login" />
		<font color="#FF0000"><c:out value="${loginError}"></c:out></font>
			Data de nasc:<form:input type='date' path="dataTemporaria" />
		<font color="#FF0000"><c:out value="${dataError}"></c:out></font>
		<br />
			Senha:<form:input type='password' path="usuario.senha" />
		<font color="#FF0000"><c:out value="${passWordError}"></c:out></font>
			Email:<form:input path="email" />
		<form:errors path="email" cssStyle="color:red" />
		<br />
		<label for="nome">Nome:</label>
		<form:input path="nome" />
		<form:errors path="nome" cssStyle="color:red" />
		<br />
			CPF:<form:input path="cpf" />
		<form:errors path="cpf" cssStyle="color:red" />
		<br />
			Tel. Fixo:<form:input path="telFixo" />
		<br />
			Celular:<form:input path="telCelular" />
		<br />
			VAUCHER:<form:input path="vaucher" />
		<font color="#FF0000"><c:out value="${vaucherError}"></c:out></font>
		<br />
		<input type='submit' value='salvar'>
	</form:form>

	<form:form action="${url_1}" method="GET">
		<input type="submit" value="Voltar" />
	</form:form>

	<c:if test="${modalidade != null}">
		<input type='hidden' value='salvar'>
	</c:if>
</body>
</html>