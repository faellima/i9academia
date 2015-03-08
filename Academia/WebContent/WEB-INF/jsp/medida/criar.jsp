
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Novo medida</title>
</head>
<body>

	<c:url var="url_1" value="/medida/criar" />

	<form:form action="${url_1}" method="POST" modelAttribute="medida">
		Descricao:<form:input path="nome" />
		<font color="#FF0000"><c:out value="${nameError}"></c:out></font>
		<input type="submit" value="adicionar" />
	</form:form>

	<c:url var="url_voltar" value="/medida" />
	<form:form action="${url_voltar}" method="POST">
		<input type="submit" value="Voltar" />
	</form:form>

</body>
</html>