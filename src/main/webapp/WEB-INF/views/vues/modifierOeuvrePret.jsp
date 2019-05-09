<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Modification d'une oeuvre en pret </H1>
<form method="post" action="submitModifierOeuvrePret.htm">
    <div class="col-md-12 well well-md">
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Titre de l'oeuvre </label>
            <div class="col-md-3">
                <INPUT type="hidden" name="idOeuvrepret" value="${Oeuvre.idOeuvrepret}" id="idOeuvrepret" class="form-control" min="0">
                <INPUT type="text" name="txttitre" value="${Oeuvre.titreOeuvrepret}" id="titre" class="form-control" min="0">
            </div>

        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Proprietaire  </label>
            <div class="col-md-3">
                <select name="proprietaires" class="form-control">
                    <c:forEach items="${lesProprietaires}" var="proprietaire">
                        <option id=${proprietaire.getIdProprietaire().toString()} value=${proprietaire.getIdProprietaire()}  ${ Oeuvre.proprietaire.getIdProprietaire().equals(proprietaire.getIdProprietaire()) ? 'selected="selected"' : ''}>
                                ${proprietaire.getNomProprietaire()} ${proprietaire.getPrenomProprietaire()}
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
            <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                Modifier
            </button>

            <button type="button" class="btn btn-danger"
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