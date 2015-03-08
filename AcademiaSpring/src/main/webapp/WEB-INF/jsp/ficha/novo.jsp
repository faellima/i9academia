
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
	
	<c:url var="url_1" value="/ficha/adicionarFicha" />
	<c:url var="url_voltar" value="/ficha" />
	
	<font color="#FF0000"><c:out value="${carrinhoError}"></c:out></font>
	<br/>
	
	<form:form action="${url_1}" method="POST" modelAttribute="fichaExercicio">
	
		Aluno:<form:select path="ficha.aluno.id" items="${alunos}"/>
		
		Exercicio:<form:select path="exercicio.id" items="${exercicios}"/>
		<br /><br />
		Numero de series: <form:input path="qtdSeries"/><font color="#FF0000"><c:out value="${SerieError}"></c:out></font>
		
		Qtde de repeticoes:<form:input path="qtdRepeticoes"/><font color="#FF0000"><c:out value="${repeticaoError}"></c:out></font>
		
		<input type="submit" value="adicionar"/>
	</form:form>
	
	<c:if test="${not empty carrinho}">
		<table align="center" width="81%" cellspacing="0" cellpadding="0" border="1">
			<tr>
				<th>Exercicio</th>
				<th>Numero de Séries</th>
				<th>Quantidade de Repetições</th>
				<th>Ações</th>
			</tr>
			<c:set var="linha" value="0"/>
			<c:forEach var="exercicio" items="${carrinho}">
			<tr>
				<td>${exercicio.exercicio.nome}</td>
				<td>${exercicio.qtdSeries}</td>
				<td>${exercicio.qtdRepeticoes}</td>
				<td>  
				    <c:url var="url_2" value="/ficha/${linha}/remover" />
					<form:form action="${url_2}" method="Post">
						<input type="submit" value="Remover"/>
					</form:form>
					
				</td>
			</tr>
			<c:set var="linha" value="${linha + 1}"/>
			</c:forEach>
		</table>
		</c:if>
		
		<c:url var="url_3" value="/ficha/salvar" />
		<form:form action="${url_3}" method="POST" >
				<input type="submit" value="Salvar"/>
		</form:form>
		
		<form action="${url_voltar}" method='get'>
				<input type='submit' value='voltar'>
		</form>

</body>
</html>