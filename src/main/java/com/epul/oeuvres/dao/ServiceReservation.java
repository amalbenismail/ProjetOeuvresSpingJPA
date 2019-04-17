package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.ReservationEntity;
import com.epul.oeuvres.metier.ReservationventeEntity;

import javax.persistence.EntityTransaction;
import java.util.List;


public class ServiceReservation extends EntityService  {

    public void insertReservation(ReservationventeEntity uneReservervationVente) throws MonException {
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.persist(uneReservervationVente);
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

    public List<ReservationventeEntity> getReservationsEnAttente() throws MonException {

        List<ReservationventeEntity> lesReservationsEnAttente = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            lesReservationsEnAttente = (List<ReservationventeEntity>)
                    entitymanager.createQuery(
                            "SELECT r FROM ReservationventeEntity r " +
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
