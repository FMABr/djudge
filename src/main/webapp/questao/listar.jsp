<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.util.List"%>
<%@page import="model.dao.QuestaoDAO"%>
<%@page import="model.bean.Questao"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset = "utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Djudge - Juiz Online</title>

        <link rel="stylesheet" href="../css/fontawesome-all.css">
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="../css/header.css">
        <link rel="stylesheet" href="../css/bootstrap-4.3.1-dist/bootstrap-grid.css"/>
        <link rel="stylesheet" href="../css/bootstrap/bootstrap.css"/>
        <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css"/>


        <script src="../js/bootstrap/bootstrap.js"></script>
        <script src="../js/jquery/jquery-3.4.1.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
        <script src="https://use.fontawesome.com/releases/v5.5.0/js/all.js"></script> 
        <script src="../js/bootstrap/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <main>
            <div class="container-fluid">
                <div class="col-12 row justify-content-center">
                    <div class="card col-7 py-4 px-4 text-black">
                        <div class="mb-4">
                            <a class="font-weight-bold h4">Quest�es</a><br>
                            <small>Deslize para baixo para ver mais quest�es</small>
                        </div>
                        <div class="table-responsive-lg">
                            <table class="table table-hover" id="filtro">
                                <caption>Quest�es</caption>
                                <thead>
                                    <tr>
                                        <th>T�tulo<i class="fa fa-sort float-right"></i></th>
                                        <th>N�vel<i class="fa fa-sort float-right"></i></th>
                                        <th>Selecione<i class="fa fa-sort float-right"></i></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        QuestaoDAO qDAO = new QuestaoDAO();
                                        List<Questao> questoes = qDAO.getListPublicas();
                                        for (Questao q : questoes) {
                                    %>
                                    <tr>
                                        <td><%=IOUtils.toString(q.getTitulo(), "ISO-8859-1")%></td>
                                        <td><%=q.getNivel()%></td>
                                        <td class="text-center"><a class="btn btn-primary" id="background-blue" href="modelo.jsp?id=<%=q.getId()%>">Ver mais</a></td>
                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="../footer.jsp"/>
        <!--Filtro generico-->
        <script src="../js/addons/datatables.min.js"></script>
        <script src="../js/filtro/filtro_generico.js"></script>
    </body>
</html>
