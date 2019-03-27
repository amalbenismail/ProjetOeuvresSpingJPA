<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>

<div class="jumbotron text-center">
    <h1>Listing des oeuvres en pret</h1>
</div>

<div class="container">
    <a class="btn btn-secondary" href="../index.jsp" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
    <div class="container">
        <table class="table table-hover">
            <tr>
                <th class="col-md-1">Titre</th>
                <th class="col-md-2">Prénom propriétaire</th>
                <th class="col-md-4">Nom propriétaire</th>
                <th class="col-md-4">Réserver/Modifier</th>

            </tr>

            <c:forEach items="${mesOeuvresPret}" var="item">
                <tr>
                    <td>${item.titreOeuvrepret}</td>
                    <td>${item.proprietaire.prenomProprietaire}</td>
                    <td>${item.proprietaire.nomProprietaire}</td>
                    <td><a class="btn btn-info" href="reserverOeuvrePret.htm?id=${item.idOeuvrepret}" role="button"><span
                            class="glyphicon glyphicon-pencil"></span> Réserver</a>
                        <a class="btn btn-danger" href="modifierOeuvrePret.htm?id=${item.idOeuvrepret}" role="button"><span
                                class="glyphicon glyphicon-remove-circle"></span> Modifier</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>