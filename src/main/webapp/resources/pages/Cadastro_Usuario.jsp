<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <%@include file="links_header.jsp"%>

    <!-- link css -->
    <link rel="stylesheet" href="../css/styleCadastro_Usuario.css">

    <title>Informações de Cadastro</title>
</head>

<body>
<div class="vh-100 d-flex justify-content-center align-items-center flex-column" id="basebox">
    <div class="d-flex justify-content-center align-items-center row">
        <div class="col-sm-8 col-md-6 col-lg-4 p-4 mt-auto shadow rounded" id="cadastrobox">
            <form id="formcadastro">
                <h1 id="infocadastro">Informações de Cadastro</h1>
                <div class="mb-4">
                    <label class="form-label">Nome completo*</label>
                    <input type="text" class="form-control" id="nomecompleto" placeholder="">
                </div>
                <div class="mb-4">
                    <label class="form-label">Data de nascimento*</label>
                    <input type="date" class="form-control" id="dtnasc" placeholder="">
                </div>
                <div class="mb-4">
                    <label for="genero" class="form-label">Gênero</label>
                    <select class="form-select" id="genero" name="genero">
                        <option selected disabled>Selecione seu gênero</option>
                        <option value="masculino">Masculino</option>
                        <option value="feminino">Feminino</option>
                        <option value="outro">Outro</option>
                        <option value="nao-informar">Prefiro não informar</option>
                    </select>
                </div>
                <div class="mb-4">
                    <label class="form-label">E-mail*</label>
                    <input type="email" class="form-control" id="email" placeholder="nome@email.com">
                </div>
                <div class="mb-4">
                    <label class="form-label">Senha (+ de 6 caracteres)*</label>
                    <input type="password" class="form-control" id="senha" placeholder="********">
                </div>
                <div class="mb-4">
                    <label class="form-label">Repita a senha*</label>
                    <input type="password" class="form-control" id="senha2" placeholder="********">
                </div>
                <div class="form-floating d-flex justify-content-center align-items-center flex-column">
                    <a class="btn btn-secondary btn-lg w-50 btn-danger mt-4 mb-4" href="#" role="link" id="linkcontinuar">Continuar</a>
                </div>
            </form>
        </div>
        <div class="col-sm-8 col-md-6 col-lg-3 p-4 mt-auto" id="logobox">
            <div class="row d-flex align-items-bottom" id="linhalogos">
                <div class="col form-floating d-flex justify-content-center align-items-center flex-column">
                    <img src="../Imagens/Logo/FinGold branco sem fundo 1 (1).png" class="rounded float-start" id="logofingold" alt="Logo Fingold">
                </div>
                <div class="col form-floating d-flex justify-content-center align-items-center flex-column">
                    <img src="../Imagens/Logo/Miniatura.png" class="rounded float-end" id="miniaturafingold"alt="Miniatura Fingold" width="40%">
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="links_footer.jsp"%>
</body>

</html>