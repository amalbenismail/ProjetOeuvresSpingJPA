<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>

<div class="jumbotron text-center">
    <h1>Listing des oeuvres en vente</h1>
</div>

<div class="container">
    <a class="btn btn-secondary" href="../index.jsp" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
    <h2>Tableau des Oeuvres en vente</h2>
    <div class="container">
        <h3>Liste des Oeuvres en Vente</h3>
        <table class="table table-hover">
            <tr>
                <th class="col-md-1">Titre</th>
                <th class="col-md-2">Prénom adherent</th>
                <th class="col-md-4">Nom adherent</th>
                <th class="col-md-4">Réserver/Modifier</th>

            </tr>

            <c:forEach items="${lesReservations}" var="item">
                <tr>
                    <td>${item.oeuvreventeByIdOeuvrevente.titreOeuvrevente}</td>
                    <td>${item.adherentByIdAdherent.nomAdherent}</td>
                    <td>${item.adherentByIdAdherent.prenomAdherent}</td>
                    <td><a class="btn btn-info" href="submitConfirmation.htm?id=${item.oeuvreventeByIdOeuvrevente.idOeuvrevente}" role="button"><span
                            class="glyphicon glyphicon-pencil"></span> Confirmer</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>