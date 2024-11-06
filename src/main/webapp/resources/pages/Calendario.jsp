<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="links_header.jsp"%>
    <title>Calendário</title>
    <!-- css do datepicker -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datepicker/css/bootstrap-datepicker.min.css">

    <!-- css da tela -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styleCalendario.css">

    <!-- css do header e do footer -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header_footer.css">
</head>

<body>
<%@include file="header.jsp"%>

<!-- começo conteúdo -->
<!-- primeiro container -->
<div class="container mt-5 text-center">
    <div class="row text-center">
        <div class="col-12 col-sm-12 col-md-12 order-2 col-lg-6 mt-2">
            <!-- imagem principal do Calendário -->
            <div class="imagemprincipal mt-5">
                <img src="${pageContext.request.contextPath}/resources/Imagens/Vetores/Imagem Calendário.png" class="img-fluid">
            </div>
        </div>
        <div class="col-12 col-sm-12 col-md-12 order-1 col-lg-6 mt-5">
            <!-- conteúdo da direita - título e subtítulo -->
            <section class="conteudodireito mt-5 mx-3">
                <article class="textoprincipal">
                    <h1>
                        Calendário de <br>
                        <h1 class="digitando">Satisfação</h1>
                    </h1>
                </article>

                <article class="subtitulo mt-3">
                    Analise sua renda diária e identifique oportunidades de melhoria.
                </article>
            </section>
        </div>
    </div>
</div>
<!-- fim primeiro container -->
<!-- segundo container (calendario) -->
<div class = "d-flex justify-content-center align-items-center flex-column mt-4" id="calendario">
    <!-- datepicker -->
    <div id="datepicker"></div>
    <!-- script do jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/datepicker/js/bootstrap-datepicker.min.js"></script>
    <script>$('#datepicker').datepicker()</script>
    <div>
        <script>
            $('#sandbox-container div').datepicker({
                language: "pt-BR",
                calendarWeeks: true,
                todayHighlight: true,
                toggleActive: true
            });
        </script>
    </div>
</div>
<!-- fim do segundo container (calendario) -->
<!-- terceiro container -->
<div class="container mt-5 text-center">
    <div class="row text-center">
        <div class="col-12 order-lg-1 order-2 col-lg-6">
            <div class="imagemprincipal2">
                <img src="${pageContext.request.contextPath}/resources/Imagens/Vetores/Imagem Calendário2.png">
            </div>
        </div>
        <div class="col-12 order-lg-2 order-1 col-lg-6 mt-5">
            <section class="conteudodireito mt-5 mx-3 mb-5">
                <article class="subtitulo2 mt-3">
                    <div class="positivo">
                        <img src="${pageContext.request.contextPath}/resources/Imagens/Icones/positivo.png" class=" me-3">
                        <h1>
                            Se a renda for positiva, ou seja, os ganhos forem maiores que os gastos, o dia está marcado com dourado. <br><br>
                        </h1>
                    </div>
                    <div class="negativo">
                        <h1>
                            Se a renda for negativa, ou seja, os gastos forem maiores que os ganhos, o dia está marcado com vermelho.
                        </h1>
                            <img src="${pageContext.request.contextPath}/resources/Imagens/Icones/negativo.png" class="ms-3">
                    </div>
                </article>
            </section>
        </div>
    </div>
</div>
<!-- fim do terceiro container -->

<%@include file="footer.jsp"%>

<%@include file="links_footer.jsp"%>
</body>

</html>