package observer;


import events.ChangeEvent;

public interface Observer<E extends ChangeEvent> {
    void update(E e);
}