package com.epul.oeuvres.controle;


import com.epul.oeuvres.dao.Service;
import com.epul.oeuvres.dao.ServiceOeuvre;
import com.epul.oeuvres.dao.ServiceReservationPret;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.OeuvrepretEntity;
import com.epul.oeuvres.metier.ReservationpretEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

@Controller

public class GestionPret {


    @RequestMapping(value = "reserverOeuvrePret.htm")
    public ModelAndView emprunterOeuvrePret(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {

            int id = Integer.parseInt(request.getParameter("id"));
            ServiceOeuvre unServiceOeuvre = new ServiceOeuvre();
            request.setAttribute("Oeuvre", unServiceOeuvre.consulterOeuvrePret(id));
            request.setAttribute("lesProprietaires", unServiceOeuvre.getProprietaires() );
            Service unService = new Service();
            request.setAttribute("lesAdherents", unService.consulterListeAdherents() );

            String r = unServiceOeuvre.getOeuvrePretReservationDatesById(id);
            request.setAttribute("reservation",r );


            destinationPage = "/vues/emprunterOeuvrePret";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "submitReservationPret.htm")
    public ModelAndView submitReservationPret(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;

        try{
            // Ajout d'une reservation dans la table reservation pret
            Service unService = new Service();
            ServiceOeuvre unServiceOeuvre = new ServiceOeuvre();
            ServiceReservationPret unServiceReservationPret = new ServiceReservationPret();
            ReservationpretEntity uneReservationPret = new ReservationpretEntity();
            OeuvrepretEntity uneOeuvrePret = new OeuvrepretEntity();
            uneOeuvrePret = unServiceOeuvre.consulterOeuvrePret(Integer.parseInt(request.getParameter("idOeuvrePret")));

            uneReservationPret.setAdherent(unService.adherentById( Integer.parseInt(request.getParameter("adherents"))   ));
            uneReservationPret.setOeuvrepret(uneOeuvrePret);
            String dateDebut = request.getParameter("dateDebut");
            String dateFin = request.getParameter("dateFin");
            SimpleDateFormat reserDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date debut = reserDateFormat.parse(dateDebut);
            java.util.Date fin = reserDateFormat.parse(dateFin);
            java.sql.Date sqlDateDebut = new java.sql.Date(debut.getTime());
            java.sql.Date sqlDateFin = new java.sql.Date(fin.getTime());

            uneReservationPret.setDateDebut(sqlDateDebut);
            uneReservationPret.setDateFin(sqlDateFin);

            unServiceReservationPret.insertReservationPret(uneReservationPret);
            destinationPage = "index";

        }
        catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }



        return new ModelAndView(destinationPage);
    }


}