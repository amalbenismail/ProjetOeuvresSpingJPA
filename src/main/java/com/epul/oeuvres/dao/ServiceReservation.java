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
    //1
    // confirmerLaReservation

    public ReservationventeEntity getReservation(int id) throws MonException {
        List<ReservationventeEntity> reservationvente = null;

        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            reservationvente = (List<ReservationventeEntity>)entitymanager.createQuery("SELECT a FROM ReservationventeEntity a WHERE a.idReservation="+id).getResultList();
            entitymanager.close();
        }catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservationvente.get(0);
    }

    public void confirmerLaReservation(ReservationventeEntity reservationvente) throws MonException {
        try
        {
            EntityTransaction transac2 = startTransaction();
            transac2.begin();
            entitymanager.merge(reservationvente);
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


}
