package repository;

import domain.Utilizator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UtilizatorRepository implements Repository<Integer, Utilizator> {
    private SessionFactory sessionFactory;

    public UtilizatorRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void adauga(Utilizator entity) {

    }

    @Override
    public void sterge(Integer integer) {

    }

    @Override
    public void modifica(Utilizator entity) {

    }

    @Override
    public Utilizator cautaDupaId(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Utilizator> obtineTot() {
        return null;
    }

    public Utilizator cautaDupaUsernameSiParola(String username, String password){
        Utilizator utilizator = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                utilizator = session.createQuery("FROM Utilizator AS u WHERE u.username = :username AND " +
                                "u.parola = :parola", Utilizator.class).setParameter("username", username).
                        setParameter("parola", password).setMaxResults(1).uniqueResult();
                System.out.println("Am gasit userul " + utilizator);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la cautare "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return utilizator;
    }
}
