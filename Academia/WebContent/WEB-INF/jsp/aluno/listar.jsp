<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>Lista de alunos</title>
		<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
       <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/morris/morris.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<c:url var="url_1" value="/aluno/form" />

	<c:url var="url_filtro" value="/aluno/filtro" />


<div class="row">
	<div class="col-xs-12">
	    <div class="box">
	        <div class="box-header">
	            <h3 class="box-title">Lista de Alunos</h3>
	            <div class="box-tools pull-right" align="right">
	                <div class="input-group">
	                    <div class="pull-right">
	                    	<form:form action="${url_filtro}" method='get'
							modelAttribute="alunomodalidade">
						
							Aluno:<form:input path="aluno.nome" />

							Modalidade:<form:select path="modalidade.id">
								<form:option value="">TODOS</form:option>
								<form:options items="${modalidades}" />
							</form:select>

							<input type="submit" value="pesquisar" />
						</form:form>
	                    </div>
	                </div>
	            </div>
	        </div>
	<div class="box-body table-responsive no-padding">
		<c:if test="${not empty alunos}">
		<table class="table table-hover">
			<tr>
				<th>Nome</th>
				<th>Cpf</th>
				<th>Tel. Fixo</th>
				<th>Celular</th>
				<th>Email</th>
				<th>Acao de Edicao</th>
				<!-- 	<th>Acao de Matricula</th> -->
				<th>Acao de Exclusao</th>
			</tr>
			<c:forEach var="aluno" items="${alunos}">
				<tr>
					<td>${aluno.nome}</td>
					<td>${aluno.cpf}</td>
					<td>${aluno.telFixo}</td>
					<td>${aluno.telCelular}</td>
					<td>${aluno.email}</td>
					<td><c:url var="url_2" value="/aluno/${aluno.id}" /> 
					<form:form
							action="${url_2}" method="POST">
							<input type="submit" value="Editar"></input>
					</form:form></td>

					<c:url var="url_3" value="/aluno" />

					<!--  <td><form:form action="${url_3}" method="GET">
						<input type="submit" value="Matricular"/>
					</form:form></td> -->

					<td><form:form action="${url_2}" method="DELETE">
							<input type="submit" value="Excluir" />
					</form:form></td>
				</tr>
			</c:forEach>
		</table>
		</div>
		</div>
		</div>
		</div>
	</c:if>
	<br />

	<table>
			<tr>
				<td><form:form action="${url_1}" method="POST">
				<input type="submit" value="Novo" />
				</form:form></td>
				<td></td>
				<td></td>
				<td><c:url var="url_voltar" value="/aluno/voltar" />
				<form:form action="${url_voltar}" method="GET">
				<input type="submit" value="Voltar" />
				</form:form></td>
			</tr>
	</table>
	
	<!-- <div class="input-group">
		<form:form action="${url_1}" method="POST">
			<input type="submit" value="Novo" />
		</form:form>

		<c:url var="url_voltar" value="/aluno/voltar" />
		<form:form action="${url_voltar}" method="GET">
			<input type="submit" value="Voltar" />
		</form:form>
	</div> -->
	
	<c:if test="${empty alunos}">
		<c:out value="Não existem alunos cadastradas" />
	</c:if>
	<script src="resources/js/jquery-2.0.2.min.js" type="text/javascript"></script>
   <script src="resources/js/bootstrap.min.js" type="text/javascript"></script>
   <script src="resources/js/AdminLTE/app.js" type="text/javascript"></script>
   <script src="resources/js/AdminLTE/demo.js" type="text/javascript"></script>

</body>
</html>