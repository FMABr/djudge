<%-- 
    Document   : index
    Created on : 02/07/2019, 14:59:43
    Author     : Junior
--%>

<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.util.Base64"%>
<%@page import="model.dao.QuestaoImagemDAO"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="model.dao.QuestaoSaidaEsperadaDAO"%>
<%@page import="model.dao.QuestaoEntradaDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.dao.QuestaoRestricaoDAO"%>
<%@page import="model.dao.QuestaoDAO"%>
<%@page import="model.bean.QuestaoRestricao"%>
<%@page import="model.bean.QuestaoSaidaEsperada"%>
<%@page import="model.bean.QuestaoEntrada"%>
<%@page import="model.bean.QuestaoImagem"%>
<%@page import="model.dao.GenericDAO"%>
<%@page import="model.bean.Questao"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset = "utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Djudge - Juiz Online</title>

        <link rel="stylesheet" href="../../css/fontawesome-all.css">
        <link rel="stylesheet" href="../../css/style.css">
        <link rel="stylesheet" href="../../css/header.css">
        <link rel="stylesheet" href="../../css/bootstrap-4.3.1-dist/bootstrap-grid.css"/>
        <link rel="stylesheet" href="../../css/bootstrap/bootstrap.css"/>
        <script src="../../js/bootstrap/bootstrap.js"></script>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
        <script src="../../js/jquery/jquery-3.4.1.min.js"></script>
        <script src="../../js/popper.min.js"></script>
        <script src="../../js/bootstrap/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <main>
            <div class="container-fluid">
                <div class="row justify-content-center">
                    <div class="card col-lg-7 px-4 py-4 text-black">
                        <%
                            Questao q = new Questao();
                            GenericDAO<Questao> gqDAO = new GenericDAO();
                            q = gqDAO.findById(Questao.class, Long.parseLong(request.getParameter("id")));

                            QuestaoImagem qi = new QuestaoImagem();
                            GenericDAO<QuestaoImagem> gqiDAO = new GenericDAO();
                            qi = gqiDAO.findById(QuestaoImagem.class, Long.parseLong(request.getParameter("id")));

                            QuestaoRestricao qr = new QuestaoRestricao();
                            GenericDAO<QuestaoRestricao> gqrDAO = new GenericDAO();
                            qr = gqrDAO.findById(QuestaoRestricao.class, Long.parseLong(request.getParameter("id")));

                            QuestaoEntrada qe = new QuestaoEntrada();
                            GenericDAO<QuestaoEntrada> gqeDAO = new GenericDAO<>();
                            qe = gqeDAO.findById(QuestaoEntrada.class, Long.parseLong(request.getParameter("id")));

                            QuestaoSaidaEsperada qs = new QuestaoSaidaEsperada();
                            GenericDAO<QuestaoSaidaEsperada> gqsDAO = new GenericDAO<>();
                            qs = gqsDAO.findById(QuestaoSaidaEsperada.class, Long.parseLong(request.getParameter("id")));

                            String codeDecode = new String(q.getCodigoFonteGabarito(), "ISO-8859-1");
                        %>

                        <form action="/djudge/SubmissaoServlet?id=<%=q.getId()%>" enctype="multipart/form-data" method="POST">  
                            <div id="oculto">
                                <div class="form-row">
                                    <div class="col-lg-6">
                                        <a class="font-weight-bold h4">Quest�o</a><br>
                                        <small> Deslize para baixo para ver a quest�o por completo</small>
                                    </div>

                                    <div class="col-lg-6">
                                        <ul class="nav justify-content-end">
                                            <div class="dropdown dropup">
                                                <a class="w-25 p-3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <i class="fas fa-ellipsis-v"></i>
                                                </a>
                                                <div class="dropdown-menu">
                                                    <a class="dropdown-item" href="../../questao/cadastro.jsp"><i class="fas fa-plus-circle"></i> Novo</a>
                                                    <a class="dropdown-item" href="../../questao/editar.jsp?id=<%=q.getId()%>"><i class="fas fa-edit"></i> Editar</a>
                                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#excluir"><i class="fas fa-trash"></i> Excluir</a>
                                                    <a class="dropdown-item" href="index.jsp"><i class="fas fa-home"></i> In�cio</a>
                                                </div>
                                            </div>
                                        </ul>
                                    </div>
                                </div>

                                <div class="modal fade" id="excluir" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Excluir</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                Voc� deseja realmente excluir esta quest�o?
                                                <br>
                                                <b><%=IOUtils.toString(q.getTitulo(), "ISO-8859-1")%></b>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-danger"><i class="fas fa-trash"></i> Sim, excluir</button>
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fas fa-window-close fa-lg"></i> Cancelar</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <br><br>

                                <h4 class="text-center"><%=IOUtils.toString(q.getTitulo(), "ISO-8859-1")%></h4>

                                <p class="text-justify">
                                    <%=IOUtils.toString(q.getEnunciado(), "ISO-8859-1")%>
                                </p>

                                <div class="row justify-content-center mt-4">
                                    <div class="col-md-4">
                                        <img src="<%=IOUtils.toString(qi.getImagem(), "UTF-8")%>" width="100%" height="100%">
                                    </div>
                                </div>

                                <h5>Entrada</h5>
                                <p class="text-justify px-3">
                                    <%=IOUtils.toString(q.getEntrada(), "ISO-8859-1")%>
                                </p>

                                <h5>Sa�da</h5>
                                <p class="text-justify px-3">
                                    <%=IOUtils.toString(q.getSaida(), "ISO-8859-1")%>
                                </p>   

                                <h5>Restri��es</h5>
                                <p class="text-justify px-3">
                                    <%
                                        QuestaoRestricaoDAO qDAO = new QuestaoRestricaoDAO();
                                        List<QuestaoRestricao> restricoes = qDAO.getRestricaoByQuestao(request.getParameter("id"));
                                        for (QuestaoRestricao r : restricoes) {
                                    %>
                                    *  <%=IOUtils.toString(r.getRestricao(), "ISO-8859-1")%>  <br>                            
                                    <%}%>
                                </p>

                                <h5>Exemplos</h5>

                                <table class="table table-bordered">
                                    <tr>
                                        <td><strong>Entrada</strong></td>
                                        <td><strong>Sa�da</strong></td>
                                    </tr>
                                    <%
                                        QuestaoEntradaDAO qeDAO = new QuestaoEntradaDAO();
                                        List<QuestaoEntrada> entradas = qeDAO.getEntradaByQuestao(request.getParameter("id"));
                                        for (QuestaoEntrada e : entradas) {
                                    %>    
                                    <%
                                        QuestaoSaidaEsperadaDAO qsDAO = new QuestaoSaidaEsperadaDAO();
                                        List<QuestaoSaidaEsperada> saidas = qsDAO.getSaidaByQuestao(request.getParameter("id"));
                                        for (QuestaoSaidaEsperada s : saidas) {
                                    %>     
                                    <tr>       
                                        <td><textarea class="form-control" rows="5" readonly="true"><%=IOUtils.toString(e.getEntrada(), "ISO-8859-1")%></textarea></td>
                                        <td><textarea class="form-control" rows="5" readonly="true"><%=IOUtils.toString(s.getSaidaEsperada(), "ISO-8859-1")%></textarea></td>
                                    </tr>
                                    <%}%>
                                    <%}%>
                                </table>
                            </div>
                            <br>

                            <div class="col-12 text-center">
                                <a href="#" id="imprimir">Imprimir quest�o</a>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="../../footer.jsp"/>
    </body>

    <script>
        document.getElementById('imprimir').onclick = function () {
            var conteudo = document.getElementById('oculto').innerHTML,
                    tela_impressao = window.open('Quest�o');

            tela_impressao.document.write(conteudo);
            tela_impressao.window.print();
            tela_impressao.window.close();
        };
    </script>
</html>
