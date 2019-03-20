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


    @RequestMapping(value = "listerOeuvres.htm")
    public ModelAndView listerOeuvres(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            ServiceOeuvre unServiceOeuvre = new ServiceOeuvre();
            request.setAttribute("mesOeuvresVentes", unServiceOeuvre.consulterListeOeuvres());
            destinationPage = "/vues/listerOeuvres";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }



}
