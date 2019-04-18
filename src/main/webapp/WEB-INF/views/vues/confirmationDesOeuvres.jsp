<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>

<body>
<%@include file="navigation.jsp"%>

<div class="jumbotron text-center">
    <h1>Confirmation des ventes</h1>
</div>

<div class="container">
    <a class="btn btn-secondary" href="../index.jsp" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
    <div class="container">
        <table class="table table-hover">
            <tr>
                <th><input type="checkbox" name="checkall" id="checkall" value=""></th>
                <th class="col-md-1">Titre</th>
                <th class="col-md-2">Prénom adherent</th>
                <th class="col-md-4">Nom adherent</th>
                <th class="col-md-4">Date réservation</th>

            </tr>

            <c:forEach items="${lesReservations}" var="item">
                <tr>
                    <td><input type="checkbox" name="idReservation" value="${item.idReservation}"> </td>
                    <td>${item.oeuvrevente.titreOeuvrevente}</td>
                    <td>${item.adherent.nomAdherent}</td>
                    <td>${item.adherent.prenomAdherent}</td>
                    <td>${item.dateReservation}
                    </td>
                </tr>
            </c:forEach>
        </table>
        <button class="btn btn-info" id="confirmerOeuvre" type="button" ><span
                class="glyphicon glyphicon-pencil"></span> Confirmer</button>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>