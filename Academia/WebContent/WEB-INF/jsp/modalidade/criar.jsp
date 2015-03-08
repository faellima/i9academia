<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nova Modalidade</title>
</head>
<body>

	<c:url var="url" value="/modalidade/criar" />
	<form:form action="${url}" method="POST" modelAttribute="modalidade">
		<form:input path="nome" />
		<font color="#FF0000"><c:out value="${nameError}"></c:out></font>
		<form:errors path="nome" />
		<input type="submit" value="salvar" />
	</form:form>

	<c:url var="url_voltar" value="/modalidade" />
	<form:form action="${url_voltar}" method="GET">
		<input type="submit" value="Voltar" />
	</form:form>

</body>
</html>