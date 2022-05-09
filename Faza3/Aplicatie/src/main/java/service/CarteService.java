package service;

import domain.Carte;
import domain.StatusCarte;
import events.CarteChangeEvent;
import events.ChangeEventType;
import observer.Observable;
import observer.Observer;
import repository.CarteRepository;

import java.util.ArrayList;
import java.util.List;

public class CarteService implements Observable<CarteChangeEvent> {
    private CarteRepository carteRepository;
    private final List<Observer<CarteChangeEvent>> observers;

    public CarteService(CarteRepository carteRepository) {
        this.carteRepository = carteRepository;
        observers = new ArrayList<>();
    }

    public Iterable<Carte> obtineToateCartile(){
        return carteRepository.obtineTot();
    }

    public Iterable<Carte> obtineToateCartileLibere(){
        List<Carte> carti = new ArrayList<>();
        carteRepository.obtineTot().forEach(carte -> {
            if (carte.getStatusCarte() == StatusCarte.LIBERA)
                carti.add(carte);
        });
        return carti;
    }

    public Carte obtineCarteDupaId(Integer id){
        return carteRepository.cautaDupaId(id);
    }

    public Iterable<Carte> cautaCartiDupaTitluSauAutor(String text){
        List<Carte> carti = new ArrayList<>();
        carteRepository.obtineTot().forEach(carte -> {
            if (carte.getTitlu().contains(text) || carte.getAutor().contains(text))
                carti.add(carte);
        });
        return carti;
    }

    public void adaugaCarte(String titlu, String autor, String editura){
        Carte carte = new Carte(titlu, autor, editura, StatusCarte.LIBERA);
        carteRepository.adauga(carte);

        notifyObservers(new CarteChangeEvent(ChangeEventType.ADD, carte));
    }

    public void stergeCarte(Integer id){
        carteRepository.sterge(id);
        notifyObservers(new CarteChangeEvent(ChangeEventType.DELETE, null));
    }

    public void modificaCarte(Integer id, String titlu, String autor, String editura, StatusCarte statusCarte){
        var carte = new Carte(id, titlu, autor, editura, statusCarte);
        carteRepository.modifica(carte);
        notifyObservers(new CarteChangeEvent(ChangeEventType.UPDATE, carte));
    }

    public void modificaStatusCarti(List<Carte> carti, StatusCarte statusCarte){
        carti.forEach(carte -> {
            carte.setStatusCarte(statusCarte);
            carteRepository.modifica(carte);
        });
        notifyObservers(new CarteChangeEvent(ChangeEventType.UPDATE, null));
    }

    @Override
    public void addObserver(Observer<CarteChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<CarteChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(CarteChangeEvent t) {
        observers.forEach(x -> x.update(t));
    }
}
