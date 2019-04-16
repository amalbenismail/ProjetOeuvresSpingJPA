package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.*;

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


    public OeuvrepretEntity consulterOeuvrePret(int id)  throws MonException
    {
        List<OeuvrepretEntity> oeuvrepretEntityList = null;
        OeuvrepretEntity oeuvrepretEntity = new OeuvrepretEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            oeuvrepretEntityList = (List<OeuvrepretEntity>)entitymanager.createQuery("SELECT o FROM OeuvrepretEntity o WHERE o.idOeuvrepret="+id).getResultList();
            oeuvrepretEntity = oeuvrepretEntityList.get(0);
            entitymanager.close();
        }catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oeuvrepretEntity;

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

    public void insererOeuvreVente(OeuvreventeEntity uneOeuvreVente) throws MonException {
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.persist(uneOeuvreVente);
            transac.commit();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifierOeuvreVente(OeuvreventeEntity uneOeuvreVente) throws MonException {
        try
        {
            EntityTransaction transac2 = startTransaction();
            transac2.begin();
            entitymanager.merge(uneOeuvreVente);
            transac2.commit();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /* Consultation d'une propriztaire par son id
     */
    public ProprietaireEntity getProprietaireById(int id) throws MonException {
        List<ProprietaireEntity> proprietaires = null;

        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            proprietaires = (List<ProprietaireEntity>)entitymanager.createQuery("SELECT a FROM ProprietaireEntity a WHERE a.idProprietaire="+id).getResultList();
            entitymanager.close();
        }catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proprietaires.get(0);
    }


    public List<OeuvrepretEntity> consulterListeOeuvresPret() throws MonException {

        List<OeuvrepretEntity> mesOeuvresPret = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesOeuvresPret = (List<OeuvrepretEntity>)
                    entitymanager.createQuery(
                            "SELECT o FROM OeuvrepretEntity o " +
                                    "ORDER BY o.titreOeuvrepret").getResultList();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesOeuvresPret;
    }

    public void modifierOeuvrePret(OeuvrepretEntity uneOeuvrePret) throws MonException {
        try
        {
            EntityTransaction transac2 = startTransaction();
            transac2.begin();
            entitymanager.merge(uneOeuvrePret);
            transac2.commit();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<ReservationEntity> getReservationsEnAttente() throws MonException {

        List<ReservationEntity> lesReservationsEnAttente = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            lesReservationsEnAttente = (List<ReservationEntity>)
                    entitymanager.createQuery(
                            "SELECT r FROM ReservationEntity r " +
                                    "WHERE r.statut='en attente'").getResultList();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lesReservationsEnAttente;
    }




}
