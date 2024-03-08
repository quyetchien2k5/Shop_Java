package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Shop {
    private final SimpleIntegerProperty idshop;
    private final SimpleStringProperty nameshop;
    private String address;
    private int phoneNumber;
    private User owner;
    private int numberofproduct;
    private String dateStart;
    private String email;

    public Shop(int idshop, String nameshop, String address, int phoneNumber, User owner, int numberofproduct, String dateStart, String email) {
        this.idshop = new SimpleIntegerProperty(idshop);
        this.nameshop = new SimpleStringProperty(nameshop);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.owner = owner;
        this.numberofproduct = numberofproduct;
        this.dateStart = dateStart;
        this.email = email;
    }

    public Shop(int idshop, String nameshop) {
        this.idshop = new SimpleIntegerProperty(idshop);
        this.nameshop = new SimpleStringProperty(nameshop);
    }

    public int getId() {
        return idshop.get();
    }

    public SimpleIntegerProperty idProperty() {
        return idshop;
    }

    public void setId(int id) {
        this.idshop.set(id);
    }

    public String getName() {
        return nameshop.get();
    }

    public SimpleStringProperty nameProperty() {
        return nameshop;
    }

    public void setName(String name) {
        this.nameshop.set(name);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getNumberofproduct() {
        return numberofproduct;
    }

    public void setNumberofproduct(int numberofproduct) {
        this.numberofproduct = numberofproduct;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
