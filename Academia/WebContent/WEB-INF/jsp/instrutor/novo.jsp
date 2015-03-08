
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Instrutor</title>
</head>
<body>

	<c:url var="url" value="/instrutor/criar" />

	<form:form action="${url}" method='post' modelAttribute="instrutor">
		Login:<form:input path="usuario.login" />
		<font color="#FF0000"><c:out value="${loginError}"></c:out></font>
		Data de nasc:<form:input type='date' path="dataTemporaria" />
		<font color="#FF0000"><c:out value="${dateError}"></c:out></font>
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
		<input type='submit' value='salvar'>
	</form:form>

	<c:if test="${modalidade != null}">
		<input type='hidden' value='salvar'>
	</c:if>
</body>
</html>