package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.ReservationpretEntity;

import javax.persistence.EntityTransaction;

public class ServiceReservationPret extends EntityService  {


    public void insertReservationPret(ReservationpretEntity uneReservervationPret) throws MonException {
        try
        {
            EntityTransaction transaction = startTransaction();
            transaction.begin();
            entitymanager.persist(uneReservervationPret);
            transaction.commit();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture ", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
