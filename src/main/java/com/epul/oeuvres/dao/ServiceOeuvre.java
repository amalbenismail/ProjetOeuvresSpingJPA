package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.OeuvreventeEntity;
import com.epul.oeuvres.metier.ProprietaireEntity;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceOeuvre extends EntityService {


    public List<OeuvreventeEntity> consulterListeOeuvres() throws MonException {

        List<OeuvreventeEntity> mesOeuvresVentes = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesOeuvresVentes = (List<OeuvreventeEntity>)
                    entitymanager.createQuery(
                            "SELECT o FROM OeuvreventeEntity o " +
                                    "ORDER BY o.titreOeuvrevente").getResultList();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesOeuvresVentes;
    }

    public OeuvreventeEntity consulterOeuvre(int id)  throws MonException
    {
        List<OeuvreventeEntity> oeuvreventes = null;
        OeuvreventeEntity oeuvrevente = new OeuvreventeEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            oeuvreventes = (List<OeuvreventeEntity>)entitymanager.createQuery("SELECT o FROM OeuvreventeEntity o WHERE o.idOeuvrevente="+id).getResultList();
            oeuvrevente = oeuvreventes.get(0);
            entitymanager.close();
        }catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oeuvrevente;

    }


    public List<ProprietaireEntity> getProprietaires() throws MonException {
        List<ProprietaireEntity> lesProprietaires = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            lesProprietaires = (List<ProprietaireEntity>)
                    entitymanager.createQuery(
                            "SELECT p FROM ProprietaireEntity p " +
                                    "ORDER BY p.nomProprietaire").getResultList();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lesProprietaires;
    }
}
