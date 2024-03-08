package Controller;

import Model.ConnectSQL;
import Model.ModelProduct;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerProduct {
    public Label nameproduct;
    public Label typeproduct;
    public Label sizeproduct;
    public Spinner<Integer> numberproduct;
    public Label gender;
    public Label price;
    public AnchorPane product;
    public ImageView imageproductshow;
    public Label quantity;
    public int quantityinput = 10;
    public String imagePathproduct;
    public Label age;
    public Label shopname;


    ModelProduct modelProduct = new ModelProduct();

    public List<ModelProduct> getProducts() {
        List<ModelProduct> modelProducts = new ArrayList<>();
        String SELECT_PRODUCTS_QUERY = "SELECT * FROM product";

        try (Connection connection = ConnectSQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Process results
            while (resultSet.next()) {
                ModelProduct modelProduct = new ModelProduct();
                // Populate product object from result set columns
                modelProduct.setIdProduct(resultSet.getInt("idproduct"));
                modelProduct.setNameProduct(resultSet.getString("nameproduct"));
                modelProduct.setStyle(resultSet.getString("style"));
                modelProduct.setSize(resultSet.getString("size"));
                modelProduct.setGender(resultSet.getString("gender"));
                modelProduct.setPrice(resultSet.getInt("price"));
                modelProduct.setImage(resultSet.getString("image"));
                modelProduct.setQuantity(resultSet.getInt("quantity"));
                modelProduct.setAge(resultSet.getString("age"));
                modelProduct.setNameshop(resultSet.getString("shopname"));

                modelProducts.add(modelProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }

        return modelProducts;
    }

    public void setProductData(ModelProduct modelProduct) {
        nameproduct.setText(modelProduct.getNameProduct());
        typeproduct.setText(modelProduct.getStyle());
        sizeproduct.setText(modelProduct.getSize());
        gender.setText(modelProduct.getGender());
        price.setText(String.valueOf(modelProduct.getPrice()));
        quantity.setText(String.valueOf(modelProduct.getQuantity()));
        age.setText(modelProduct.getAge());
        shopname.setText(modelProduct.getNameshop());


        // Cập nhật đầu vào số lượng và value factory
        quantityinput = Integer.parseInt(quantity.getText());

        try {
            imagePathproduct = modelProduct.getImage(); // Lấy đường dẫn từ CSDL
            Image image = new Image(imagePathproduct);
            imageproductshow.setImage(image);
            imageproductshow.setPreserveRatio(true); // Đảm bảo tỷ lệ khung hình được bảo toàn
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // Xử lý hoặc ghi nhận ngoại lệ một cách thích hợp
        }

        // Thiết lập giá trị Spinner
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, quantityinput, 1);
        numberproduct.setValueFactory(valueFactory);
    }
    public void inputcart() {
        Connection connection = ConnectSQL.getConnect();

        String addProduct = "INSERT INTO cart(nameproduct, style, gender, size, image, price, quantity, age, username, shopname) VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(addProduct)) {
            preparedStatement.setString(1, nameproduct.getText());
            preparedStatement.setString(2, typeproduct.getText());
            preparedStatement.setString(3, gender.getText());
            preparedStatement.setString(4, sizeproduct.getText());
            preparedStatement.setString(5, imagePathproduct);
            preparedStatement.setString(6, price.getText());
            preparedStatement.setInt(7, numberproduct.getValue());
            preparedStatement.setString(8, age.getText());
            preparedStatement.setString(9, ControllerLogin.nameow);
            preparedStatement.setString(10, shopname.getText());
            System.out.println(ControllerLogin.nameow);

            int check = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void handleBuyButtonAction(ActionEvent actionEvent) throws SQLException {
        System.out.println("Đã nhấn mua sản phẩm: " + nameproduct.getText());
        System.out.println("Số lượng: " + numberproduct.getValue());
        inputcart();

    }
}
