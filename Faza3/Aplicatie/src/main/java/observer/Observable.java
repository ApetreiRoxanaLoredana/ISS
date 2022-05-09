package observer;


import events.ChangeEvent;

public interface Observable<E extends ChangeEvent> {
    void addObserver(Observer<E> e);
    void removeObserver(Observer<E> e);
    void notifyObservers(E t);
}
