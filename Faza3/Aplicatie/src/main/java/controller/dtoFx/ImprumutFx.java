package controller.dtoFx;

import javafx.scene.control.CheckBox;

import java.time.LocalDate;

public class ImprumutFx {
    private Integer idBorrow;
    private Integer idUser;
    private String username;
    private Integer idBook;
    private String titleBook;
    private CheckBox selectButton;
    private LocalDate date;

    public ImprumutFx(Integer idBorrow, Integer idUser, String username, Integer idBook, String titleBook, LocalDate date) {
        this.idBorrow = idBorrow;
        this.idUser = idUser;
        this.username = username;
        this.idBook = idBook;
        this.titleBook = titleBook;
        this.selectButton = new CheckBox();
        this.date = date;
    }

    public Integer getIdBorrow() {
        return idBorrow;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public CheckBox getSelectButton() {
        return selectButton;
    }

    public LocalDate getDate() {
        return date;
    }
}
