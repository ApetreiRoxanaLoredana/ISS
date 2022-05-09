package repository;

import domain.Carte;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class CarteRepository implements Repository<Integer, Carte>{
    private SessionFactory sessionFactory;

    public CarteRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void adauga(Carte entity) {
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
    public void sterge(Integer id) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Carte carte = session.load( Carte.class, id );
                System.out.println("Stergem cartea " + carte.getId());
                session.delete(carte);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la stergere "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void modifica(Carte entity) {
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
    public Carte cautaDupaId(Integer id) {
        Carte carte = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                carte = session.load( Carte.class, id );
                System.out.println("Am gasit cartea " + carte);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la cautare "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return carte;
    }

    @Override
    public Iterable<Carte> obtineTot() {

        List<Carte> carti = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                carti = session.createQuery("FROM Carte AS c ORDER BY c.titlu ASC", Carte.class).list();
                System.out.println(carti.size() + " carti am gasit:");
                for (Carte c: carti) {
                    System.out.println(c);
                }
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la select "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return carti;
    }
}
