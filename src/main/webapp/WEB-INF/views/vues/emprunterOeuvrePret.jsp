<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<!--  jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>


<!-- DateRange -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<!-- Bootstrap Date-Picker Plugin -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

<H1> Reservation d'une oeuvre en pret</H1>
<form method="post" action="submitReservationPret.htm">
    <div class="col-md-12 well well-md">
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Titre de l'oeuvre </label>
            <div class="col-md-3">
                <INPUT type="hidden" name="idOeuvrePret" value="${Oeuvre.idOeuvrepret}" id="idOeuvrePret" class="form-control" min="0">
                <INPUT type="text" name="txttitre" disabled value="${Oeuvre.titreOeuvrepret}" id="titre" class="form-control" min="0">
            </div>

        </div>

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Le proprietaire</label>
            <div class="col-md-3">
                <select name="select_proprietaire" class="form-control">
                    <c:forEach items="${lesProprietaires}" var="proprietaire">
                        <option id=${proprietaire.getIdProprietaire().toString()} value=${proprietaire.getIdProprietaire()} >
                                ${proprietaire.getPrenomProprietaire()} ${proprietaire.getNomProprietaire()}
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
            <label class="col-md-3 control-label">Date début </label>
            <div class="col-md-3">
                <input  name="dateDebut" required class="datedebut form-control" min="0" placeholder="jj/mm/aaaa">
            </div>
        </div>

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Date fin </label>
            <div class="col-md-3">
                <input  name="dateFin"  required class="datefin form-control" min="0" placeholder="jj/mm/aaaa">
            </div>
        </div>

        <script>

            $(document).ready(function(){


                /*var date = $("#DateReservation").val();
                var dateArray = date.split("/");
                var dateDebut = dateArray[0];
                var dateFin = dateArray[1];
                var dateRange = [];
                console.log("datedebut",dateArray);

                // populate the array
                for (var d = new Date(dateDebut); d <= new Date(dateFin); d.setDate(d.getDate() + 1)) {

                    var date = new Date().toISOString().substr(0, 19);

                    console.log("date : ",date);
                    //dateRange.push( $('.datedebut').datepicker.format('yyyy-mm-dd', d));
                }
                */


                var options={
                    format: 'yyyy-mm-dd',
                    todayHighlight: true,
                    autoclose: true,
                };

                $('.datedebut').datepicker({
                    format: 'yyyy-mm-dd',
                    minDate: 0,
                    autoclose: true,
                    onSelect: function (date) {
                        var date2 = $('.datedebut').datepicker('getDate');
                        date2.setDate(date2.getDate() + 1);
                        $('.datefin').datepicker('setDate', date2);
                        //sets minDate to dt1 date + 1
                        $('.datefin').datepicker('options', 'minDate', date2);
                    }

                });
                $('.datefin').datepicker(options);




              /* $('input[name="daterange"]').daterangepicker({
                   locale:{
                       format: 'YYYY-MM-DD',
                       separator: ' to '
                   },
                    isInvalidDate: function(date) {
                        var disabled_start = moment('2019-04-17', 'YYYY-MM-DD');
                        var disabled_end = moment('2019-04-25', 'YYYY-MM-DD');
                        return date.isAfter(disabled_start) && date.isBefore(disabled_end);
                    }
                });*/


            });





        </script>

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