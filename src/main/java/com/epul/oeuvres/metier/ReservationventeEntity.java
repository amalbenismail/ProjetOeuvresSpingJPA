package com.epul.oeuvres.metier;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "reservationvente", schema = "baseoeuvre", catalog = "")
public class ReservationventeEntity {
    private int idReservation;
    private Date dateReservation;
    private String statut;
    private AdherentEntity adherent;
    private OeuvreventeEntity oeuvrevente;

    @Id
    @Column(name = "idReservation")
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    @Basic
    @Column(name = "date_reservation")
    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    @Basic
    @Column(name = "statut")
    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationventeEntity that = (ReservationventeEntity) o;
        return idReservation == that.idReservation &&
                Objects.equals(dateReservation, that.dateReservation) &&
                Objects.equals(statut, that.statut);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idReservation, dateReservation, statut);
    }

    @ManyToOne
    @JoinColumn(name = "id_oeuvrevente", referencedColumnName = "id_oeuvrevente", nullable = false)
    public OeuvreventeEntity getOeuvrevente() {
        return oeuvrevente;
    }
    public void setOeuvrevente(OeuvreventeEntity oeuvrevente) {
        this.oeuvrevente = oeuvrevente;
    }

    @ManyToOne
    @JoinColumn(name = "id_adherent", referencedColumnName = "id_adherent", nullable = false)
    public AdherentEntity getAdherent() {
        return adherent;
    }
    public void setAdherent(AdherentEntity adherent) {
        this.adherent = adherent;
    }

}
