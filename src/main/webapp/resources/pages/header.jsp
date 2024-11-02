<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- começo header -->
<nav class="navbar fixed-top cabeçalho">
  <div class="container-fluid header">
    <!-- conteúdo da esquerda -->
    <div class="esquerda">
      <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
              data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
        <img src="../Imagens/Icones/Menu.png" width="80%" class="img-fluid">
      </button>

      <!-- começo offcanvas -->
      <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasNavbar"
           aria-labelledby="offcanvasNavbar">
        <div class="offcanvas-header">
          <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Midas Fingold</h5>
          <button type="button" class="btn-close fechar" data-bs-dismiss="offcanvas"
                  aria-label="Close"></button>
        </div>

        <div class="linha-horizontal"></div>

        <!-- conteúdo offcanvas -->
        <div class="offcanvas-body">
          <ul class="navbar-nav justify-content-start flex-grow-1 pe-3">
            <li class="nav-item">
              <i class="bi bi-house-fill"></i>
              <a class="nav-link" aria-current="page" href="#">Home</a>
            </li>
            <li class="nav-item perfil">
              <i class="bi bi-person-circle"></i>
              <a class="nav-link" href="#">Perfil</a>
            </li>
            <!-- menu dropdown -->
            <li class="nav-item dropdown">
              <i class="bi bi-cash-stack"></i>
              <a class="nav-link" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Finanças >
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item my-3" href="Ganhos.jsp">Ganhos</a></li>
                <li>
                </li>
                <li><a class="dropdown-item" href="Gastos.jsp">Gastos</a></li>
              </ul>
            </li>
            <!-- fim menu dropdown -->

            <li class="nav-item calendario">
              <i class="bi bi-calendar3"></i>
              <a class="nav-link" href="Calendario.jsp">Calendário</a>
            </li>

            <li class="nav-item">
              <i class="bi bi-bullseye"></i>
              <a class="nav-link" href="Objetivos.jsp">Objetivos</a>
            </li>

            <li class="nav-item">
              <i class="bi bi-piggy-bank-fill"></i>
              <a class="nav-link" href="#">Investimentos</a>
            </li>

            <li class="nav-item notificacoes">
              <i class="bi bi-bell-fill"></i>
              <a class="nav-link" href="#">Notificações</a>
            </li>
          </ul>
        </div>
      </div>
      <!-- fim offcanvas -->
      <div class="logo mx-4">
        <img src="../Imagens/Logo/FinGold branco sem fundo 1 (1).png" width="80%" class="img-fluid">
      </div>
    </div>
    <!-- fim conteúdo da esquerda -->

    <!-- conteúdo da direita -->
    <div class="direita px-3">
      <div class="icones mx-4">
        <i class="bi bi-calendar3"></i>
      </div>
      <div class="icones2 mx-4">
        <i class="bi bi-bell-fill"></i>
      </div>
      <div class="icones3 mx-1 text-center">
        <i class="bi bi-person-circle mx-3"></i>
        <h5>Perfil</h5>
      </div>
    </div>
    <!-- fim conteúdo da direita -->
  </div>
</nav>
<!-- fim header -->