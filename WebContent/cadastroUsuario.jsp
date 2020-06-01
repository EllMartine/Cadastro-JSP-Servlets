<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/estilo.css">

<!-- Adicionando JQuery -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
</head>
<body>
	<!--<h1>Cadastro de usuário</h1>-->
	
	<nav class="menu">
	<ul>
		<li><a href="index.jsp"><img src="resources/imagens/porta.svg" width="20px"></a></li>
		<li><a href="acessoLiberado.jsp"><img src="resources/imagens/home.png" width="20px"></a></li>
	</ul>
	</nav>
	
	<center><h3>${mensagem}</h3></center>
	
	<form action="ServletUsuario" method="post" id="formUser" onsubmit="return validarCampos() ? true : false" enctype="multipart/form-data">

	<div class="container3">
	
	<img class="imgForm" src="resources/imagens/usuario.png" width="80px">
		
			<input class="input" type="text" readonly="readonly" id="id" name="id" placeholder="ID" value="${usuario.id}">
		
			<input class="input" type="text" id="login" name="login" placeholder="LOGIN" value="${usuario.login}">
			
			<input class="input" type="password" id="senha" name="senha" placeholder="SENHA" value="${usuario.senha}">
			
			<input class="input" type="text" id="nome" name="nome" placeholder="NOME" value="${usuario.nome}">
			
			<input class="input" type="text" id="telefone" name="telefone" placeholder="CONTATO" value="${usuario.telefone}">
			
			<!-- Implementando WB -->
			
			<input class="input" type="text" id="cep" name="cep" placeholder="CEP" onblur=" consultarCep()" value="${usuario.cep}">
	
			<input type="file" id="img" name="img">
	
	</div>
	
	<div class="container3">
	
	<img class="imgForm" id="imgLocal" src="resources/imagens/local.png" width="80px">
	
			<input class="input" type="text" id="rua" name="rua" placeholder="RUA" value="${usuario.rua}">
			
			<input class="input" type="text" id="bairro" name="bairro" placeholder="BAIRRO" value="${usuario.bairro}">
			
			<input class="input" type="text" id="cidade" name="cidade" placeholder="CIDADE" value="${usuario.cidade}">
			
			<input class="input" type="text" id="estado" name="estado" placeholder="ESTADO" value="${usuario.estado}">
			
			<input class="input" type="text" id="ibge" name="ibge" placeholder="IBGE" value="${usuario.ibge}">
			
			<button class="button" type="submit">Cadastrar</button>
			<button class="button" onclick="document.getElementById('formUser').action='ServletUsuario?acao=cancelar'">Cancelar</button>
	</div>
	
	</form>	
	
	<table>
			<th>ID</th><th>LOGIN</th><th>IMAGEM</th><th>NOME</th><th>CONTATO</th><th>EXCLUIR</th><th>EDITAR</th><th>TELEFONES</th>
		<c:forEach items="${usuarios}" var="usuario">
			<tr>
				<td><c:out value="${usuario.id}"></c:out></td>
				<td><c:out value="${usuario.login}"></c:out></td>
				<td><img src="<c:out value="${usuario.arquivo}" ></c:out>" width="30px" height="30px"></td>
				<td><c:out value="${usuario.nome}"></c:out></td>
				<td><c:out value="${usuario.telefone}"></c:out></td>
				<td><a href="ServletUsuario?acao=delete&usuario=${usuario.id}"><img src="resources/imagens/excluir.jpg" width="20px"></a></td>
				<td><a href="ServletUsuario?acao=editar&usuario=${usuario.id}"><img src="resources/imagens/editar.png" width="20px"></a></td>
				<td><a href="ServletTelefone?acao=addTel&usuario=${usuario.id}"><img src="resources/imagens/telefone.png" width="20px"></a></td>
			</tr>
		</c:forEach>
	</table>
	
	<script type="text/javascript">
	
	function validarCampos() {
		
		if (document.getElementById("login").value == "") {
			alert('Informe o login');
			return false;
		} else if (document.getElementById("senha").value == "") {
			alert('Informe a senha');
			return false;
		} else if (document.getElementById("nome").value == "") {
			alert('Informe o nome');
			return false;
		} else if (document.getElementById("telefone").value == "") {
			alert('Informe o telefone');
			return false;
		}
		
		return true;
		
	}
	
	function consultarCep() {
		
		var cep = $("#cep").val();
		
        //Consulta o webservice viacep.com.br/
        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

            if (!("erro" in dados)) {
                //Atualiza os campos com os valores da consulta.
                $("#rua").val(dados.logradouro);
                $("#bairro").val(dados.bairro);
                $("#cidade").val(dados.localidade);
                $("#estado").val(dados.uf);
                $("#ibge").val(dados.ibge);
            } //end if.
            else {
                //CEP pesquisado não foi encontrado.
            	limpa_formulário_cep();
                alert("CEP não encontrado.");
            }
        });

	}
	</script>	
</body>
</html>