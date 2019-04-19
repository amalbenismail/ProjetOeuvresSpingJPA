package com.epul.oeuvres.metier;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "reservationpret", schema = "baseoeuvre", catalog = "")
public class ReservationpretEntity {
    private int idReservationPret;
    private Date dateDebut;
    private Date dateFin;
    private AdherentEntity adherent;
    private OeuvrepretEntity oeuvrepret;

    @Id
    @Column(name = "idReservationPret")
    public int getIdReservationPret() {
        return idReservationPret;
    }

    public void setIdReservationPret(int idReservationPret) {
        this.idReservationPret = idReservationPret;
    }

    @Basic
    @Column(name = "dateDebut")
    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    @Basic
    @Column(name = "dateFin")
    public Date getDateFin() {
        return dateFin;
    }

    @ManyToOne
    @JoinColumn(name = "id_oeuvrepret", referencedColumnName = "id_oeuvrepret", nullable = false)
    public OeuvrepretEntity getOeuvrepret() {
        return oeuvrepret;
    }
    public void setOeuvrepret(OeuvrepretEntity oeuvre) {
        this.oeuvrepret = oeuvre;
    }

    @ManyToOne
    @JoinColumn(name = "id_adherent", referencedColumnName = "id_adherent", nullable = false)
    public AdherentEntity getAdherent() {
        return adherent;
    }
    public void setAdherent(AdherentEntity adherent) {
        this.adherent = adherent;
    }


    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationpretEntity that = (ReservationpretEntity) o;
        return idReservationPret == that.idReservationPret &&
                Objects.equals(dateDebut, that.dateDebut) &&
                Objects.equals(dateFin, that.dateFin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idReservationPret, dateDebut, dateFin);
    }
}
