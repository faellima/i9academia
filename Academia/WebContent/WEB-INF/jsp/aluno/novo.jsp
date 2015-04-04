
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de aluno</title>
	<link href="/Academia/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="/Academia/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link href="/Academia/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />
	<link href="/Academia/resources/css/morris/morris.css" rel="stylesheet" type="text/css" />
	<link href="/Academia/resources/css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
	<link href="/Academia/resources/css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" />
	<link href="/Academia/resources/css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
	<link href="/Academia/resources/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"	rel="stylesheet" type="text/css" />
	<link href="/Academia/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<c:url var="url" value="/aluno/criar" />

	<div class="box box-primary">
		<div class="box-header">
			<h3 class="box-title">Novo Aluno</h3>
		</div>
		<form:form action="${url}" method='post' modelAttribute="aluno">
			<div class="box-body">
				<div class="form-group">
                     <label for="loginInput">Login</label>
                     <form:input type="text" class="form-control" id="loginInput" path="usuario.login" placeholder="Insira seu login"/>
                 </div>
				<div class="form-group">
                     <label>Data de nasc</label>
                     <form:input class="form-control" type='date' path="dataTemporaria"/>
                 </div>
				<div class="form-group">
                     <label for="loginInput">Senha</label>
                     <form:input class="form-control" type='password' path="usuario.senha" />
                 </div>
				<div class="form-group">
                    <label for="exampleInputEmail1">Email</label>
                    <form:input path="email" type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email"/>
                </div>
<%-- 				<font color="#FF0000"><c:out value="${loginError}"></c:out></font> --%>
<%-- 				<font color="#FF0000"><c:out value="${dataError}"></c:out></font> --%>
<%-- 				<font color="#FF0000"><c:out value="${passWordError}"></c:out></font> --%>
<%-- 				<form:errors path="email" cssStyle="color:red" /> --%>
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
				<input class="btn btn-primary" type='submit' value='salvar'>
				<form:form action="/academia/aluno" method="GET">
					<input class="btn btn-primary" type="submit" value="Voltar" />
				</form:form>
			</div>
		</form:form>
	
	
		<c:if test="${modalidade != null}">
			<input type='hidden' value='salvar'>
		</c:if>	
	</div>
</body>
</html>