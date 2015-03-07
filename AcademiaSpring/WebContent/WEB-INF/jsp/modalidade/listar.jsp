<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 
<head>
	<body>
		
		<c:url var="url_filtro" value="/modalidade/filtro" />
	
		<form:form action="${url_filtro}" method='get' modelAttribute="modalidade">
	
		Nome:<form:input path="nome"/>

		<input type="submit" value="pesquisar"/>
		</form:form>
		
		<c:url var="url_1" value="/modalidade/form" />
		
		<c:if test="${not empty modalidades}">
		<table align="center" width="81%" cellspacing="0" cellpadding="0" border="1">
		<tbody>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Acao de Edicao</th>
				<th>Acao de Exclusao</th>
			</tr>
			
			<c:forEach var="modalidade" items="${modalidades}">
					<tr>
						<td>${modalidade.id}</td>
						<td>${modalidade.nome}</td>
						<td>
							<c:url var="url_2" value="/modalidade/${modalidade.id}" />
				    		 <form:form action="${url_2}" method="POST">
								<input type="submit" value="Editar"/>
							</form:form></td>
							<td><form:form action="${url_2}" method="DELETE">
								<input type="submit" value="Excluir"/>
							</form:form>
						</td>
					</tr>
		 </c:forEach>
		 </tbody>
		</table>
		</br>
		</c:if>
	
		<form:form action="${url_1}" method="POST">
		<input type="submit" value="Novo"/>
		</form:form>
	
		<c:if test="${empty modalidades}">
			<c:out value="NÃO EXISTEM MODALIDADES"></c:out>
		</c:if>
		
	</body>
</head>