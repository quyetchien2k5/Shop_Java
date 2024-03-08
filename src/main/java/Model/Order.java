package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order extends ModelProduct{
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
    private SimpleStringProperty address;
    private SimpleStringProperty buyer;

    public Order() {
    }

    public Order(int idProduct, String nameProduct, String gender, String size, String style, String nameshop, int price, int quantity, String age, SimpleIntegerProperty idProduct1, SimpleStringProperty nameProduct1, SimpleStringProperty style1, SimpleStringProperty size1, SimpleStringProperty gender1, SimpleStringProperty nameshop1, SimpleIntegerProperty price1, SimpleStringProperty age1, SimpleStringProperty image, SimpleIntegerProperty quantity1, SimpleStringProperty address, SimpleStringProperty buyer) {
        super(idProduct, nameProduct, gender, size, style, nameshop, price, quantity, age);
        this.address = address;
        this.buyer = buyer;
    }

    @Override
    public int getIdProduct() {
        return IdProduct.get();
    }

    @Override
    public SimpleIntegerProperty idProductProperty() {
        return IdProduct;
    }

    public void setIdProduct(int idProduct) {
        if(this.IdProduct == null){
            this.IdProduct = new SimpleIntegerProperty();
        }
        this.IdProduct.set(idProduct);
    }

    @Override
    public String getNameProduct() {
        return NameProduct.get();
    }

    @Override
    public SimpleStringProperty nameProductProperty() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        if(this.NameProduct == null){
            this.NameProduct = new SimpleStringProperty();
        }
        this.NameProduct.set(nameProduct);
    }

    @Override
    public String getStyle() {
        return Style.get();
    }

    @Override
    public SimpleStringProperty styleProperty() {
        return Style;
    }

    public void setStyle(String style) {
        if(this.Style == null){
            this.Style = new SimpleStringProperty();
        }
        this.Style.set(style);
    }

    @Override
    public String getSize() {
        return Size.get();
    }

    @Override
    public SimpleStringProperty sizeProperty() {
        return Size;
    }

    public void setSize(String size) {
        if(this.Size == null){
            this.Size = new SimpleStringProperty();
        }
        this.Size.set(size);
    }

    @Override
    public String getGender() {
        return Gender.get();
    }

    @Override
    public SimpleStringProperty genderProperty() {
        return Gender;
    }

    public void setGender(String gender) {
        if(this.Gender == null){
            this.Gender = new SimpleStringProperty();
        }
        this.Gender.set(gender);
    }

    @Override
    public String getNameshop() {
        return Nameshop.get();
    }

    @Override
    public SimpleStringProperty nameshopProperty() {
        return Nameshop;
    }

    public void setNameshop(String nameshop) {
        if(this.Nameshop == null){
            this.Nameshop = new SimpleStringProperty();
        }
        this.Nameshop.set(nameshop);
    }

    @Override
    public int getPrice() {
        return Price.get();
    }

    @Override
    public SimpleIntegerProperty priceProperty() {
        return Price;
    }

    public void setPrice(int price) {
        if(this.Price == null){
            this.Price = new SimpleIntegerProperty();
        }
        this.Price.set(price);
    }

    @Override
    public String getAge() {
        return Age.get();
    }

    @Override
    public SimpleStringProperty ageProperty() {
        return Age;
    }

    public void setAge(String age) {
        if(this.Age == null){
            this.Age = new SimpleStringProperty();
        }
        this.Age.set(age);
    }

    @Override
    public String getImage() {
        return Image.get();
    }

    @Override
    public SimpleStringProperty imageProperty() {
        return Image;
    }

    public void setImage(String image) {
        if(this.Image == null){
            this.Image = new SimpleStringProperty();
        }
        this.Image.set(image);
    }

    @Override
    public int getQuantity() {
        return Quantity.get();
    }

    @Override
    public SimpleIntegerProperty quantityProperty() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        if(this.Quantity == null){
            this.Quantity = new SimpleIntegerProperty();
        }
        this.Quantity.set(quantity);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        if(this.address == null){
            this.address = new SimpleStringProperty();
        }
        this.address.set(address);
    }

    public String getBuyer() {
        return buyer.get();
    }

    public SimpleStringProperty buyerProperty() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        if(this.buyer == null){
            this.buyer = new SimpleStringProperty();
        }
        this.buyer.set(buyer);
    }
}
