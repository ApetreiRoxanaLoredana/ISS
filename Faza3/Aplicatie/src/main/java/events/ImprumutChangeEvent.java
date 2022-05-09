package events;

import domain.Carte;
import domain.Imprumut;

public class ImprumutChangeEvent implements ChangeEvent{
    private final ChangeEventType eventType;
    private final Imprumut newImprumut;
    private Imprumut oldImprumut;

    public ImprumutChangeEvent(ChangeEventType eventType, Imprumut newImprumut) {
        this.eventType = eventType;
        this.newImprumut = newImprumut;
    }

    public ImprumutChangeEvent(ChangeEventType eventType, Imprumut newImprumut, Imprumut oldImprumut) {
        this.eventType = eventType;
        this.newImprumut = newImprumut;
        this.oldImprumut = oldImprumut;
    }
}
