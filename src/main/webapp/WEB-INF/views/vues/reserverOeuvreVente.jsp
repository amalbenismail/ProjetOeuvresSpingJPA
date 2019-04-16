<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Réservation d'une oeuvre en vente</H1>
<form method="post" action="submitReservationVente.htm">
    <div class="col-md-12 well well-md">
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Titre de l'oeuvre </label>
            <div class="col-md-3">
                <INPUT type="hidden" name="idOeuvrevente" value="${Oeuvre.idOeuvrevente}" id="idOeuvrevente" class="form-control" min="0">
                <INPUT type="text" name="txttitre" disabled value="${Oeuvre.titreOeuvrevente}" id="titre" class="form-control" min="0">
            </div>

        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div> 
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Prix </label>
            <div class="col-md-3">
                <INPUT type="text" name="prixoeuvre" disabled value="${Oeuvre.prixOeuvrevente}" id="prix" class="form-control" min="0">
            </div>
        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Adherent</label>
            <div class="col-md-3">
                <select name="adherents" class="form-control">
                    <c:forEach items="${lesAdherents}" var="adherent">
                        <option id=${adherent.getIdAdherent().toString()} value=${adherent.getIdAdherent()} >
                                ${adherent.getPrenomAdherent()} ${adherent.getNomAdherent()}
                        </option>
                    </c:forEach>
                </select>

            </div>
        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Date Réservation </label>
            <div class="col-md-3">
                <INPUT type="date" name="date"  required class="form-control" min="0">
            </div>
        </div>


        <div class="form-group">
            <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                Reserver
            </button>

            <button type="button" class="btn btn-default btn-primary"
                    onclick="{
                            window.location = '../index.htm';
                        }">
                <span class="glyphicon glyphicon-remove"></span> Annuler

            </button>
        </div>
    </div>
</form>
</body>
<%@include file="footer.jsp"%>
</html>