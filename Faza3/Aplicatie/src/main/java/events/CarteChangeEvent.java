package events;

import domain.Carte;

public class CarteChangeEvent implements ChangeEvent{
    private final ChangeEventType eventType;
    private final Carte newCarte;
    private Carte oldCarte;

    public CarteChangeEvent(ChangeEventType eventType, Carte newCarte) {
        this.eventType = eventType;
        this.newCarte = newCarte;
    }

    public CarteChangeEvent(ChangeEventType eventType, Carte newCarte, Carte oldCarte) {
        this.eventType = eventType;
        this.newCarte = newCarte;
        this.oldCarte = oldCarte;
    }
}
