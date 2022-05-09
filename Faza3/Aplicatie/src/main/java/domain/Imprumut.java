package domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Imprumut extends Entity<Integer>{
    private Integer id;
    private StatusImprumut statusImprumut;
    private Utilizator utilizator;
    private Carte carte;
    private LocalDate data;



    public Imprumut() {
    }

    public Imprumut(Integer id, StatusImprumut statusImprumut, Utilizator utilizator, Carte carte, LocalDate data) {
        this.id = id;
        this.statusImprumut = statusImprumut;
        this.utilizator = utilizator;
        this.carte = carte;
        this.data = data;
    }

    public Imprumut(StatusImprumut statusImprumut, Utilizator utilizator, Carte carte, LocalDate data) {
        this.statusImprumut = statusImprumut;
        this.utilizator = utilizator;
        this.carte = carte;
        this.data = data;
    }


    public StatusImprumut getStatusImprumut() {
        return statusImprumut;
    }

    public void setStatusImprumut(StatusImprumut statusImprumut) {
        this.statusImprumut = statusImprumut;
    }



    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }


    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    @Override
    public String toString() {
        return "Imprumut{" +
                "id=" + id +
                ", statusImprumut=" + statusImprumut +
                ", utilizator=" + utilizator +
                ", carte=" + carte +
                ", data=" + data +
                '}';
    }
}
