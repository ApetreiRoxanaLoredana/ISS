package repository;

import domain.Carte;
import domain.Imprumut;
import domain.Utilizator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ImprumutRepository implements Repository<Integer, Imprumut>{
    private SessionFactory sessionFactory;

    public ImprumutRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void adauga(Imprumut entity) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(entity);
                tx.commit();

            } catch (RuntimeException ex) {
                System.err.println("Eroare la inserare " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void sterge(Integer integer) {

    }

    @Override
    public void modifica(Imprumut entity) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.update(entity);
                tx.commit();

            } catch(RuntimeException ex){
                System.err.println("Eroare la update " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public Imprumut cautaDupaId(Integer id) {
        Imprumut imprumut = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                imprumut = session.load( Imprumut.class, id );
                System.out.println("Am gasit imprumutul " + imprumut);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la cautare "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return imprumut;
    }

    @Override
    public Iterable<Imprumut> obtineTot() {
        List<Imprumut> imprumuturi = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                imprumuturi = session.createQuery("FROM Imprumut AS imp ORDER BY imp.data DESC", Imprumut.class).list();
                System.out.println(imprumuturi.size() + " imprumuturi am gasit:");
                for (Imprumut c: imprumuturi) {
                    System.out.println(c);
                }
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la select "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return imprumuturi;
    }
}
