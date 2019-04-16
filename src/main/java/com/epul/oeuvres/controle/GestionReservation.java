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
            ServiceOeuvre unServiceOeuvre = new ServiceOeuvre();
            request.setAttribute("lesReservations", unServiceOeuvre.getReservationsEnAttente());
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
            ReservationEntity uneReservationVente = new ReservationEntity();

            uneReservationVente.setAdherentByIdAdherent(unService.adherentById( Integer.parseInt(request.getParameter("adherents"))   ));
            uneReservationVente.setOeuvreventeByIdOeuvrevente(uneOeuvreVenteAModifier);
            uneReservationVente.setStatut("en attente");
            //uneReservationVente.setDateReservation(new Date(request.getParameter("date")));
            String reserDate = request.getParameter("date");
            SimpleDateFormat reserDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date date = reserDateFormat.parse(reserDate);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            System.out.println("______________DATEE"+sqlDate);

            uneReservationVente.setDateReservation(sqlDate);
         //   uneReservationVente.setIdAdherent( Integer.parseInt(request.getParameter("adherents")));
           // uneReservationVente.setIdOeuvrevente(Integer.parseInt(request.getParameter("idOeuvrevente")));

            unServiceReservation.insertReservation(uneReservationVente);
          /*  uneReservationVente.set
            unServiceOeuvre.reserverOeuvreVente(uneOeuvreVenteAModifier);*/

            destinationPage = "index";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }






}