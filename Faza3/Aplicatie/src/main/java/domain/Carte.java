package domain;

public class Carte extends Entity<Integer>{
    private Integer id;
    private String titlu;
    private String autor;
    private String editura;
    private StatusCarte statusCarte;

    public Carte(Integer id, String titlu, String autor, String editura, StatusCarte statusCarte) {
        this.id = id;
        this.titlu = titlu;
        this.autor = autor;
        this.editura = editura;
        this.statusCarte = statusCarte;
    }

    public Carte(String titlu, String autor, String editura, StatusCarte statusCarte) {
        this.titlu = titlu;
        this.autor = autor;
        this.editura = editura;
        this.statusCarte = statusCarte;
    }

    public Carte() {
    }


    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public StatusCarte getStatusCarte() {
        return statusCarte;
    }

    public void setStatusCarte(StatusCarte statusCarte) {
        this.statusCarte = statusCarte;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "id=" + id +
                ", titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                ", editura='" + editura + '\'' +
                ", statusCarte=" + statusCarte +
                '}';
    }
}
