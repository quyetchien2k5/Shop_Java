package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ModelProduct {
    private SimpleIntegerProperty IdProduct;
    private SimpleStringProperty NameProduct;
    private SimpleStringProperty Style;
    private SimpleStringProperty Size;
    private SimpleStringProperty Gender;
    private SimpleStringProperty Nameshop;
    private SimpleIntegerProperty Price;
    private SimpleStringProperty Age;
    private SimpleStringProperty Image;
    private SimpleIntegerProperty Quantity;


    public ModelProduct(int idProduct, String nameProduct, int price, int quantity) {
        this.IdProduct = new SimpleIntegerProperty(idProduct);
        this.NameProduct = new SimpleStringProperty(nameProduct);
        this.Price = new SimpleIntegerProperty(price);
        this.Quantity = new SimpleIntegerProperty(quantity);
    }

    public ModelProduct(int idProduct, String nameProduct, String gender, String size, String style, String nameshop, int price, int quantity, String age) {
        this.IdProduct = new SimpleIntegerProperty(idProduct);
        this.NameProduct = new SimpleStringProperty(nameProduct);
        this.Nameshop = new SimpleStringProperty(nameshop);
        this.Size = new SimpleStringProperty(size);
        this.Style = new SimpleStringProperty(style);
        this.Gender = new SimpleStringProperty(gender);
        this.Price = new SimpleIntegerProperty(price);
        this.Quantity = new SimpleIntegerProperty(quantity);
        this.Age = new SimpleStringProperty(age);
    }

    public ModelProduct() {}

    public ModelProduct(int id2, String name2, String gender2, String age2, String size2, String style2, String shopname2, int price2, int quantity2) {
        this.IdProduct = new SimpleIntegerProperty(id2);
        this.NameProduct = new SimpleStringProperty(name2);
        this.Nameshop = new SimpleStringProperty(shopname2);
        this.Size = new SimpleStringProperty(size2);
        this.Style = new SimpleStringProperty(style2);
        this.Gender = new SimpleStringProperty(gender2);
        this.Price = new SimpleIntegerProperty(price2);
        this.Quantity = new SimpleIntegerProperty(quantity2);
        this.Age = new SimpleStringProperty(age2);
    }

    public String getAge() {
        return Age.get();
    }

    public SimpleStringProperty ageProperty() {
        return Age;
    }

    public void setAge(String age) {
        if(this.Age == null){
            this.Age = new SimpleStringProperty();
        }
        this.Age.set(age);
    }

    public int getIdProduct() {
        return IdProduct.get();
    }

    public SimpleIntegerProperty idProductProperty() {
        return IdProduct;
    }

    public void setIdProduct(int idProduct) {
        if (this.IdProduct == null) {
            this.IdProduct = new SimpleIntegerProperty();
        }
        this.IdProduct.set(idProduct);
    }

    public String getNameProduct() {
        return NameProduct.get();
    }

    public SimpleStringProperty nameProductProperty() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        if (this.NameProduct == null) {
            this.NameProduct = new SimpleStringProperty();
        }
        this.NameProduct.set(nameProduct);
    }

    public String getStyle() {
        return Style.get();
    }

    public SimpleStringProperty styleProperty() {
        return Style;
    }

    public void setStyle(String style) {
        if (this.Style == null) {
            this.Style = new SimpleStringProperty();
        }
        this.Style.set(style);
    }
    public String getSize() {
        return Size.get();
    }

    public SimpleStringProperty sizeProperty() {
        return Size;
    }

    public void setSize(String size) {
        if (this.Size == null) {
            this.Size = new SimpleStringProperty();
        }
        this.Size.set(size);
    }

    public String getGender() {
        return Gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return Gender;
    }

    public void setGender(String gender) {
        if (this.Gender == null) {
            this.Gender = new SimpleStringProperty();
        }
        this.Gender.set(gender);
    }



    public int getQuantity() {
        return Quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        if(this.Quantity == null){
            this.Quantity = new SimpleIntegerProperty();
        }
        this.Quantity.set(quantity);
    }


    public String getNameshop() {
        return Nameshop.get();
    }

    public void setNameshop(String nameshop) {
        if (this.Nameshop == null) {
            this.Nameshop = new SimpleStringProperty();
        }
        this.Nameshop.set(nameshop);
    }

    public SimpleStringProperty nameshopProperty() {
        return Nameshop;
    }

    public int getPrice() {
        return Price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return Price;
    }

    public void setPrice(int price) {
        if (this.Price == null) {
            this.Price = new SimpleIntegerProperty();
        }
        this.Price.set(price);
    }

    public String getImage() {
        return Image.get();
    }

    public SimpleStringProperty imageProperty() {
        return Image;
    }

    public void setImage(String image) {
        if (this.Image == null) {
            this.Image = new SimpleStringProperty();
        }
        this.Image.set(image);
    }
}
