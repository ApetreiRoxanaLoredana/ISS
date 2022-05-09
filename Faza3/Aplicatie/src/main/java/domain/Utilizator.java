package domain;

public class Utilizator extends Entity<Integer>{
    private Integer id;
    private String nume;
    private String cnp;
    private String adresa;
    private String telefon;
    private String username;
    private String parola;
    private StatusUtilizator statusUtilizator;

    public Utilizator() {
    }

    public Utilizator(Integer id, String nume, String cnp, String adresa, String telefon, String username, String parola, StatusUtilizator statusUtilizator) {
        this.id = id;
        this.nume = nume;
        this.cnp = cnp;
        this.adresa = adresa;
        this.telefon = telefon;
        this.username = username;
        this.parola = parola;
        this.statusUtilizator = statusUtilizator;
    }

    public Utilizator(String nume, String cnp, String adresa, String telefon, String username, String parola, StatusUtilizator statusUtilizator) {
        this.nume = nume;
        this.cnp = cnp;
        this.adresa = adresa;
        this.telefon = telefon;
        this.username = username;
        this.parola = parola;
        this.statusUtilizator = statusUtilizator;
    }


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public StatusUtilizator getStatusUtilizator() {
        return statusUtilizator;
    }

    public void setStatusUtilizator(StatusUtilizator statusUtilizator) {
        this.statusUtilizator = statusUtilizator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", cnp='" + cnp + '\'' +
                ", adresa='" + adresa + '\'' +
                ", telefon='" + telefon + '\'' +
                ", username='" + username + '\'' +
                ", parola='" + parola + '\'' +
                ", statusUtilizator=" + statusUtilizator +
                '}';
    }
}
