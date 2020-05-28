<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/estilo.css">
</head>
<body>

	<center><h3>${mensagem}</h3></center>
	
	<nav class="menu">
	<ul>
		<li><a href="index.jsp"><img src="resources/imagens/porta.svg" width="20px"></a></li>
		<li><a href="acessoLiberado.jsp"><img src="resources/imagens/home.png" width="20px"></a></li>
	</ul>
	</nav>
	
	<div class="container">
		
		<img class="imgForm" src="resources/imagens/telefone.png" width="80px">
		
		<form action="ServletTelefone" method="post" id="formProd" onsubmit=" return validarCampos() ? true : false">
			<input class="input" type="text" readonly="readonly" id="id" name="id" placeholder="ID" value="${usuario.nome}">
			
			<input class="input" type="text" id="numero" name="numero" placeholder="NÚMERO" value="${numero}">
			
			<select id="tipo" name="tipo">
				<option>SELECIONE UMA OPÇÃO</option>
				<option>PESSOAL</option>
				<option>CORPORATIVO</option>
			</select>
			
			<button class="button" type="submit">Cadastrar</button>
			<button class="button" onclick="document.getElementById('formProd').action='ServletUsuario?acao=cancelar'">Cancelar</button>
		</form>
	</div>
	
	<table>
			<th>ID</th><th>NÚMERO</th><th>TIPO</th><th>EXCLUIR</th>
		<c:forEach items="${telefones}" var="telefone">
			<tr>
				<td><c:out value="${telefone.id}"></c:out></td>
				<td><c:out value="${telefone.numero}"></c:out></td>
				<td><c:out value="${telefone.tipo}"></c:out></td>
	
				<td><a href="ServletUsuario?acao=delete&telefone=${telefone.id}"><img src="resources/imagens/excluir.jpg" width="20px"></a></td>
			</tr>
		</c:forEach>
	</table>
	
	<script type="text/javascript">
	
	function validarCampos() {
		
		if (document.getElementById("numero").value == "") {
			alert("Informe o numero");
			return false;
		} else if (document.getElementById("tipo").value == "") {
			alert("Informe o tipo");
			return false;
		} 
		
		return true;
	}
	
	</script>
</body>
</html>