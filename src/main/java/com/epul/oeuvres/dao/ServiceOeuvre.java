package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.OeuvreventeEntity;

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
}
