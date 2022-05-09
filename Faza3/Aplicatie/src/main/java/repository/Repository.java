package repository;

import domain.Entity;

public interface Repository<ID, E extends Entity<ID>> {
    void adauga(E entity);
    void sterge(ID id);
    void modifica(E entity);
    E cautaDupaId(ID id);
    Iterable<E> obtineTot();
}
