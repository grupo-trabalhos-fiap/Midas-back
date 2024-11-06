<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dashboard</title>

  <%@include file="links_header.jsp"%>

  <!-- CSS da página de dashboard -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styleDashboard.css">

  <!-- css do header e do footer -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header_footer.css">
</head>
<body>
<%@include file="header.jsp"%>

<section class="container topo-dash">
  <!-- topo esquerda -->
  <div class="topo-esquerda">
    <div class="topo-esquerda-textos">
      <h1 class="topo-h1">Seja bem-vindo,<br> Matheus Gonçalves!</h1>
      <p class="topo-p">Dê um toque de ouro nas suas finanças<br> e conquiste o sucesso que sempre sonhou!</p>
    </div>
    <div class="topo-esquerda-imagem">
      <img src="${pageContext.request.contextPath}/resources/Imagens/Vetores/Vetor Midas.png" class="img">
    </div>
  </div>
  <!-- topo direita -->
  <div class="topo-direita">
    <div class="topo-direita-botao">
      <h3 class="h3-topo-direita">Comparação</h3>
      <button class="botao-topo-direita">Acesse</button>
    </div>
    <div class="topo-direita-grafico">
      <img src="${pageContext.request.contextPath}/resources/Imagens/Gráficos/ganhos-gastos.svg" class="img-fluid imagem-topo-direita">
      <h4>Saldo do mês = <span>R$200,00</span></h4>
    </div>
  </div>
</section>
<section class="container graficos-meio">
  <div class="grafico1-meio">
    <div class="graficos-meio-botao">
      <h3 class="h3-graficos-meio">Análise: <span>ganhos</span></h3>
      <button class="botao-graficos-meio">Acesse</button>
    </div>
    <div class="meio-grafico">
      <img src="${pageContext.request.contextPath}/resources/Imagens/Gráficos/analise-de-ganhos.svg" class="img-fluid">
      <h4>Ganhos de março = R$2.000,00</h4>
    </div>
  </div>
  <div class="grafico2-meio">
    <div class="graficos-meio-botao">
      <!-- PAREI NAS CORES DO GRÁFICO DE GASTOS -->
      <h3 class="h3-graficos-meio">Análise: <span>gastos</span></h3>
      <button class="botao-graficos-meio">Acesse</button>
    </div>
    <div class="meio-grafico">
      <h4>Gastos de março = R$1.800,00</h4>
      <img src="${pageContext.request.contextPath}/resources/Imagens/Gráficos/analise-de-gastos.svg" class="img-fluid">

    </div>
  </div>
  <div class="grafico3-meio">
    <div class="graficos-meio-botao">
      <h3 class="h3-graficos-meio">Último <span>gasto</span></h3>
      <button class="botao-graficos-meio">Acesse</button>
    </div>
    <div class="meio-grafico-ultimo-gasto">
      <h5>R$60,00</h5>
      <h5>31/03/2024</h5>
      <h5>Ovo da páscoa</h5>
    </div>
  </div>
</section>
<section class="container graficos-inferior">
  <div class="grafico1-inferior">
    <div class="graficos-inferior-botao">
      <h3 class="h3-graficos-inferior">Objetivos</h3>
      <button class="botao-graficos-inferior">Acesse</button>
    </div>
    <div class="inferior-grafico-objetivos">
      <div class="progress" role="progressbar" aria-label="Example 50px high" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="height: 40px">
        <div class="progress-bar" style="width: 50%"></div>
      </div>
      <h4>50% dos objetivos concluídos</h4>
    </div>
  </div>
  <div class="grafico2-inferior">
    <div class="graficos-inferior-botao">
      <h3 class="h3-graficos-inferior">Investimentos</h3>
      <button class="botao-graficos-inferior">Acesse</button>
    </div>
    <div class="inferior-grafico-investimentos">
      <h3>CDB = R$200,00</h3>
      <h3>LCI = R$100,00</h3>
      <h3>LCA = R$300,00</h3>
      <h4>Valor investido = <span>R$600,00</span></h4>
    </div>
  </div>
  <div class="grafico3-inferior">
    <div class="graficos-inferior-botao">
      <h3 class="h3-graficos-inferior">Dívidas</h3>
      <button class="botao-graficos-inferior">Acesse</button>
    </div>
    <div class="inferior-grafico-dividas">
      <h3>20/05/2024 - R$200,00</h3>
      <h3>10/08/2024 - R$500,00</h3>
      <h3>02/04/2025 - R$300,00</h3>
      <h4>Valor a ser pago = R$1000,00</h4>
    </div>
  </div>
</section>
<%@include file="footer.jsp"%>

<!-- link JS do Bootstrap -->
<%@include file="links_footer.jsp"%>
</body>
</html>