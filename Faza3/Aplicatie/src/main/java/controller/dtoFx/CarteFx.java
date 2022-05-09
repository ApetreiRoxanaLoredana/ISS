package controller.dtoFx;

import domain.Carte;
import domain.Entity;
import javafx.scene.control.CheckBox;

public class CarteFx extends Entity<Integer> {

    private Integer id;
    private String titlu;
    private String autor;
    private String editura;
    private CheckBox selectButton;
    private String status;

    public CarteFx(Carte carte) {
        this.id = carte.getId();
        this.titlu = carte.getTitlu();
        this.autor = carte.getAutor();
        this.editura = carte.getEditura();
        this.selectButton = new CheckBox();
        this.status = carte.getStatusCarte().toString();
    }


    public CheckBox getSelectButton() {
        return selectButton;
    }

    public void setSelectButton(CheckBox selectButton) {
        this.selectButton = selectButton;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditura() {
        return editura;
    }

    public String getStatus() {
        return status;
    }
}
