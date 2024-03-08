package Controller;

import Model.ConnectSQL;
import Model.ModelProduct;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Model.ConnectSQL.getConnect;

public class ControllerCart {
    public ImageView imageproduct;
    public Label nameproduct;
    public Label typeproduct;
    public Label sizeproduct;
    public Label styleproduct;
    public Label genderproduct;
    public Label quantityproduct;
    public Label priceproduct;
    public Label ageproduct;
    public Label nameshop;
    public String addressuser;
    public Label erroraddress;

    public List<ModelProduct> getProducts() {
        List<ModelProduct> modelProductcart = new ArrayList<>();
        String SELECT_PRODUCTS_QUERY = "SELECT * FROM cart WHERE username = ?";
        Connection connection = ConnectSQL.getConnect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_QUERY);) {


            preparedStatement.setString(1, ControllerLogin.nameow);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Process results
            while (resultSet.next()) {
                ModelProduct modelProduct1 = new ModelProduct();
                // Populate product object from result set columns
                modelProduct1.setNameProduct(resultSet.getString("nameproduct"));
                modelProduct1.setStyle(resultSet.getString("style"));
                modelProduct1.setSize(resultSet.getString("size"));
                modelProduct1.setGender(resultSet.getString("gender"));
                modelProduct1.setPrice(resultSet.getInt("price"));
                modelProduct1.setImage(resultSet.getString("image"));
                modelProduct1.setQuantity(resultSet.getInt("quantity"));
                modelProduct1.setAge(resultSet.getString("age"));
                modelProduct1.setNameshop(resultSet.getString("shopname"));
                modelProductcart.add(modelProduct1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }

        return modelProductcart;
    }

    public void setProductData(ModelProduct modelProduct) {
        nameproduct.setText(modelProduct.getNameProduct());
        typeproduct.setText(modelProduct.getStyle());
        sizeproduct.setText(modelProduct.getSize());
        genderproduct.setText(modelProduct.getGender());
        priceproduct.setText(String.valueOf(modelProduct.getPrice()));
        quantityproduct.setText(String.valueOf(modelProduct.getQuantity()));
        ageproduct.setText(modelProduct.getAge());
        nameshop.setText(modelProduct.getNameshop());


        try {
            String imagePathproduct = modelProduct.getImage(); // Lấy đường dẫn từ CSDL
            Image image = new Image(imagePathproduct);
            imageproduct.setImage(image);
            imageproduct.setPreserveRatio(true); // Đảm bảo tỷ lệ khung hình được bảo toàn
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // Xử lý hoặc ghi nhận ngoại lệ một cách thích hợp
        }

    }
    public String getaddress(){
        String SELECT_PRODUCTS_QUERY = "SELECT address FROM user WHERE name = ?";
        Connection connection = ConnectSQL.getConnect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_QUERY);) {

            preparedStatement.setString(1, ControllerLogin.nameow);

            ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    addressuser = resultSet.getString("address");
                }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
        return addressuser;
    }
    public void inputorder() {
        Connection connection = ConnectSQL.getConnect();

        String addProduct = "INSERT INTO orders(nameproduct, style, gender, size, image, shopname, price, quantity, age, username, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(addProduct)) {
            preparedStatement.setString(1, nameproduct.getText());
            preparedStatement.setString(2, typeproduct.getText());
            preparedStatement.setString(3, genderproduct.getText());
            preparedStatement.setString(4, sizeproduct.getText());
            String image = imageproduct.getImage().getUrl();
            preparedStatement.setString(5, image);
            preparedStatement.setString(6, nameshop.getText());
            preparedStatement.setString(7, priceproduct.getText());
            preparedStatement.setInt(8, Integer.parseInt(quantityproduct.getText()));
            preparedStatement.setString(9, ageproduct.getText());
            preparedStatement.setString(10, ControllerLogin.nameow);
            preparedStatement.setString(11, getaddress());

            int check = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void buyproduct(ActionEvent actionEvent) {
        String address = getaddress();

        // Kiểm tra xem địa chỉ có phải là null hay không trước khi sử dụng isEmpty()
        if (address != null && !address.isEmpty()) {
            inputorder();
            erroraddress.setText(" ");
        }else {
             erroraddress.setText("cập nhập địa chỉ của bạn");
        }
    }
}
