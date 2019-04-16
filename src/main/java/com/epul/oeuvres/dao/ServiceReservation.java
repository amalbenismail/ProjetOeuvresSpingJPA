package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.ReservationEntity;
import javax.persistence.EntityTransaction;


public class ServiceReservation extends EntityService  {

    public void insertReservation(ReservationEntity uneReservervationVente) throws MonException {
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

}
