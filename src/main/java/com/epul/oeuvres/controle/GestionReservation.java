package com.epul.oeuvres.controle;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epul.oeuvres.dao.ServiceOeuvre;
import com.epul.oeuvres.dao.ServiceReservation;
import com.epul.oeuvres.utilitaires.FonctionsUtiles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.epul.oeuvres.dao.Service;
import com.epul.oeuvres.meserreurs.*;
import com.epul.oeuvres.metier.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;

///
/// Les méthode du contrôleur répondent à des sollicitations
/// des pages JSP

@Controller
public class GestionReservation {

    @RequestMapping(value = "reserverOeuvreVente.htm")
    public ModelAndView reserverOeuvreVente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {

            int id = Integer.parseInt(request.getParameter("id"));
            ServiceOeuvre unServiceOeuvre = new ServiceOeuvre();
            request.setAttribute("Oeuvre", unServiceOeuvre.consulterOeuvre(id));
            Service unService = new Service();
            request.setAttribute("lesAdherents", unService.consulterListeAdherents() );


            destinationPage = "/vues/reserverOeuvreVente";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "confirmerLesReservations.htm")
    public ModelAndView confirmerLesReservations(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            ServiceReservation unServiceReservation = new ServiceReservation();
            request.setAttribute("lesReservations", unServiceReservation.getReservationsEnAttente());
            destinationPage = "/vues/confirmationDesOeuvres";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "submitReservationVente.htm")
    public ModelAndView submitReservationVente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            // modification de l'état de l'oeuvre dans la table oeuvrevente
            ServiceOeuvre unServiceOeuvre = new ServiceOeuvre();
            OeuvreventeEntity uneOeuvreVenteAModifier = new OeuvreventeEntity();
            uneOeuvreVenteAModifier = unServiceOeuvre.consulterOeuvre(Integer.parseInt(request.getParameter("idOeuvrevente")));
            uneOeuvreVenteAModifier.setEtatOeuvrevente("R");
            unServiceOeuvre.modifierOeuvreVente(uneOeuvreVenteAModifier);

            // rajout d'une reservation dans la table reservation
            Service unService = new Service();
            ServiceReservation unServiceReservation = new ServiceReservation();
            ReservationventeEntity uneReservationVente = new ReservationventeEntity();

            uneReservationVente.setAdherent(unService.adherentById( Integer.parseInt(request.getParameter("adherents"))   ));
            uneReservationVente.setOeuvrevente(uneOeuvreVenteAModifier);
            uneReservationVente.setStatut("en attente");
            String reserDate = request.getParameter("date");
            SimpleDateFormat reserDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = reserDateFormat.parse(reserDate);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            uneReservationVente.setDateReservation(sqlDate);

            unServiceReservation.insertReservation(uneReservationVente);


            destinationPage = "index";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }






}
