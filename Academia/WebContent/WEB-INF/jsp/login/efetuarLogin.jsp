<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>I9 Academia</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<!-- bootstrap 3.0.2 -->
	<link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<!-- font Awesome -->
	<link href="resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<!-- Theme style -->
	<link href="resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="form-box" id="login-box">
            <div class="header">Login</div>
            <form action="efetuaLogin" method="post">
                <div class="body bg-gray">
                    <div class="form-group">
                        <input type="text" name="login" class="form-control" placeholder="Usuário"/>
                    </div>
                    <div class="form-group">
                        <input type="password" name="senha" class="form-control" placeholder="Senha"/>
                    </div>          
                </div>
                <div class="footer">                                                               
                    <button type="submit" class="btn bg-olive btn-block">Entrar</button>  
                    
                    <a href="register.html" class="text-center">Cadastrar Administrador</a>
                </div>
            </form>
			<font color="#FF0000"><c:out value="${novoAdmin}"></c:out></font>
			<font color="#FF0000"><c:out value="${loginPrincipalError}"></c:out></font>
     </div>
</body>
<script src="resources/js/bootstrap.min.js" type="text/javascript"></script>        
<script src="resources/js/jquery-2.0.2.min.js" type="text/javascript"></script>
</html>