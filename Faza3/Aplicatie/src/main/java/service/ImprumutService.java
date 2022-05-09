package service;

import domain.Imprumut;
import domain.StatusImprumut;
import events.CarteChangeEvent;
import events.ChangeEventType;
import events.ImprumutChangeEvent;
import observer.Observable;
import observer.Observer;
import repository.ImprumutRepository;

import java.util.ArrayList;
import java.util.List;

public class ImprumutService implements Observable<ImprumutChangeEvent> {
    private ImprumutRepository imprumutRepository;
    private final List<Observer<ImprumutChangeEvent>> observers;

    public ImprumutService(ImprumutRepository imprumutRepository) {
        this.imprumutRepository = imprumutRepository;
        observers = new ArrayList<>();
    }

    public Iterable<Imprumut> obtineToateImprumuturileDupaStatus(StatusImprumut statusImprumut){
        List<Imprumut> imprumuturiRez = new ArrayList<>();
        var imprumuturi = imprumutRepository.obtineTot();
        imprumuturi.forEach(imprumut -> {
            if (imprumut.getStatusImprumut() == statusImprumut)
                imprumuturiRez.add(imprumut);
        });
        return imprumuturiRez;
    }

    public void adaugaImprumut(Imprumut imprumut){
        imprumutRepository.adauga(imprumut);
        notifyObservers(new ImprumutChangeEvent(ChangeEventType.ADD, imprumut));
    }

    public Imprumut obtineImprumutDupaId(Integer id){
        return imprumutRepository.cautaDupaId(id);
    }

    public void modificaStatusImprumuturi(List<Imprumut> imprumuturi, StatusImprumut statusImprumut){
        imprumuturi.forEach(imprumut -> {
            imprumut.setStatusImprumut(statusImprumut);
            imprumutRepository.modifica(imprumut);
        });
        notifyObservers(new ImprumutChangeEvent(ChangeEventType.UPDATE, null));
    }

    @Override
    public void addObserver(Observer<ImprumutChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<ImprumutChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(ImprumutChangeEvent t) {
        observers.forEach(x -> x.update(t));
    }
}
