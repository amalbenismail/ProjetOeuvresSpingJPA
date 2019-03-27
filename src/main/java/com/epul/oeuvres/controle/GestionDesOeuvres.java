package com.epul.oeuvres.controle;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epul.oeuvres.dao.ServiceOeuvre;
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

///
/// Les méthode du contrôleur répondent à des sollicitations
/// des pages JSP

@Controller
public class GestionDesOeuvres {


    @RequestMapping(value = "listerOeuvresVente.htm")
    public ModelAndView listerOeuvresVente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            ServiceOeuvre unServiceOeuvre = new ServiceOeuvre();
            request.setAttribute("mesOeuvresVentes", unServiceOeuvre.consulterListeOeuvres());
            destinationPage = "/vues/listerOeuvresVente";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "modifierOeuvreVente.htm")
    public ModelAndView modifierOeuvreVente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ServiceOeuvre unServiceOeuvre = new ServiceOeuvre();
            request.setAttribute("Oeuvre", unServiceOeuvre.consulterOeuvre(id));
            request.setAttribute("lesProprietaires", unServiceOeuvre.getProprietaires());

            destinationPage = "/vues/modifierOeuvreVente";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "submitModifierOeuvreVente.htm")
    public ModelAndView submitModifierOeuvreVente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {

           // System.out.println("proprietaire "+request.getParameterValues("proprietaires")[0]);
            ServiceOeuvre unServiceOeuvre = new ServiceOeuvre();

            OeuvreventeEntity uneOeuvreVente = new OeuvreventeEntity();
            uneOeuvreVente.setIdOeuvrevente(Integer.parseInt(request.getParameter("idOeuvrevente")));
            uneOeuvreVente.setPrixOeuvrevente(Double.parseDouble(request.getParameter("prixoeuvre")));
            uneOeuvreVente.setTitreOeuvrevente(request.getParameter("txttitre"));
            uneOeuvreVente.setEtatOeuvrevente(unServiceOeuvre.consulterOeuvre(Integer.parseInt(request.getParameter("idOeuvrevente"))).getEtatOeuvrevente());
            ProprietaireEntity leProprietaire = new ProprietaireEntity();
            //leProprietaire.setIdProprietaire(Integer.parseInt(request.getParameterValues("proprietaires")[0]));
            //uneOeuvreVente.setProprietaire(leProprietaire);

            uneOeuvreVente.setProprietaire(unServiceOeuvre.getProprietaireById( Integer.parseInt(request.getParameter("proprietaires"))   ));

            unServiceOeuvre.modifierOeuvreVente(uneOeuvreVente);
            destinationPage = "index";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "ajouterOeuvreVente.htm")
    public ModelAndView ajouterOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            destinationPage = "vues/ajouterOeuvre";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "insererOeuvre.htm")
    public ModelAndView insererOeuvre(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            //OeuvreventeEntity oeuvre = new OeuvreventeEntity();

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        destinationPage = "index";
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "listerOeuvresPret.htm")
    public ModelAndView listerOeuvresPret(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            ServiceOeuvre unServiceOeuvre = new ServiceOeuvre();
            request.setAttribute("mesOeuvresPret", unServiceOeuvre.consulterListeOeuvresPret());
            destinationPage = "/vues/listerOeuvresPret";
        } catch (MonException e) {
            e.printStackTrace();
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "modifierOeuvrePret.htm")
    public ModelAndView modifierOeuvrePret(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ServiceOeuvre unServiceOeuvre = new ServiceOeuvre();
            request.setAttribute("Oeuvre", unServiceOeuvre.consulterOeuvrePret(id));
            request.setAttribute("lesProprietaires", unServiceOeuvre.getProprietaires());

            destinationPage = "/vues/modifierOeuvrePret";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "submitModifierOeuvrePret.htm")
    public ModelAndView submitModifierOeuvrePret(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {

            ServiceOeuvre unServiceOeuvre = new ServiceOeuvre();
            OeuvrepretEntity uneOeuvrePret = new OeuvrepretEntity();
            uneOeuvrePret.setIdOeuvrepret(Integer.parseInt(request.getParameter("idOeuvrepret")));
            uneOeuvrePret.setTitreOeuvrepret(request.getParameter("txttitre"));

            uneOeuvrePret.setProprietaire(unServiceOeuvre.getProprietaireById( Integer.parseInt(request.getParameter("proprietaires"))   ));

            unServiceOeuvre.modifierOeuvrePret(uneOeuvrePret);

            destinationPage = "index";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }






}
