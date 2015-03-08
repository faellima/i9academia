
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nova modalidade</title>
</head>
<body>
	
	<c:url var="url_1" value="/avaliacao/adicionarAvaliacaoEditada" />
	<c:url var="url_voltar" value="/avaliacao" />
	
	<c:out value="Data ${avaliacao.data}"></c:out> <br>
	<form:form action="${url_1}" method="POST" modelAttribute="medidaAvaliacao">
	   
		Aluno:<form:select path="avaliacao.aluno.id" items="${alunos}"/>
		<br /><br /><br /><br />
		Medicoes:<form:select path="medicao.id" items="${medicoes}"/>
	
		Valor:<form:input path="valor"/>
		
		<input type="submit" value="adicionar"/>
	</form:form>
	<br/>
	<c:if test="${not empty carrinho}">
		<table align="center" width="81%" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<th>Medida</th>
				<th>Valor</th>
				<th>Ações</th>
			</tr>
			<c:set var="linha" value="0"/>
			<c:forEach var="avaliacao" items="${carrinho}">
			<tr>
				<td>${avaliacao.medicao.nome}</td>
				<td>${avaliacao.valor}</td>
				<td>  
				    <c:url var="url" value="/avaliacao/${linha}/removerEditados" />
					<form:form action="${url}" method="Post">
						<input type="submit" value="Remover"/>
					</form:form>
					
				</td>
			</tr>
			<c:set var="linha" value="${linha + 1}"/>
			</c:forEach>
		</table>
		</c:if>
		<font color="#FF0000"><c:out value="${carrinhoErro}"></c:out></font>
		<font color="#FF0000"><c:out value="${valorError}"></c:out></font>
		<c:url var="url_3" value="/avaliacao" />
		<form:form action="${url_3}" method="PUT" >
				<input type="submit" value="Salvar"/>
		</form:form>
		
		<form action="${url_voltar}" method='get'>
				<input type='submit' value='voltar'>
		</form>

</body>
</html>