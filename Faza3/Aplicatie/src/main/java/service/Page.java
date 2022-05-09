package service;

import domain.Utilizator;

public class Page {
    private Utilizator utilizator;
    private CarteService carteService;
    private UtilizatorService utilizatorService;
    private ImprumutService imprumutService;

    public Page(Utilizator utilizator, CarteService carteService, UtilizatorService utilizatorService, ImprumutService imprumutService) {
        this.utilizator = utilizator;
        this.carteService = carteService;
        this.utilizatorService = utilizatorService;
        this.imprumutService = imprumutService;
    }


    public CarteService getCarteService() {
        return carteService;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public UtilizatorService getUtilizatorService() {
        return utilizatorService;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public ImprumutService getImprumutService() {
        return imprumutService;
    }
}
