package Controller;

import Model.ConnectSQL;
import Model.ModelProduct;
import Model.Order;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerOrder {

    public Label nameproduct;
    public Label typeproduct;
    public Label size;
    public Label style;
    public Label gender;
    public Label quantity;
    public Label price;
    public Label buyer;
    public Label address;
    public Label age;
    public ImageView imageorder;
    public String name;
    public String getname() {


        String SELECT_PRODUCTS_QUERY = "SELECT nameshop FROM shop WHERE owner = ?";
        Connection connection = ConnectSQL.getConnect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_QUERY);) {

            ControllerHomePage controllerHomePage = new ControllerHomePage();
            preparedStatement.setString(1, ControllerLogin.nameow);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                 name = resultSet.getString("nameshop");
            }
        }catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
        return name;
    }
    public List<Order> getorder() {
        List<Order> modelProductorder = new ArrayList<>();
        String SELECT_PRODUCTS_QUERY = "SELECT * FROM orders WHERE shopname = ?";
        Connection connection = ConnectSQL.getConnect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_QUERY);) {

                ControllerHomePage controllerHomePage = new ControllerHomePage();
            preparedStatement.setString(1, getname());

            ResultSet resultSet = preparedStatement.executeQuery();
            // Process results
            while (resultSet.next()) {
                Order order = new Order();
                // Populate product object from result set columns
                order.setNameProduct(resultSet.getString("nameproduct"));
                order.setStyle(resultSet.getString("style"));
                order.setSize(resultSet.getString("size"));
                order.setGender(resultSet.getString("gender"));
                order.setPrice(resultSet.getInt("price"));
                order.setImage(resultSet.getString("image"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setAge(resultSet.getString("age"));
                order.setAddress(resultSet.getString("address"));
                order.setBuyer(resultSet.getString("username"));
                modelProductorder.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }

        return modelProductorder;
    }
    public void setProductData(Order order) {
        nameproduct.setText(order.getNameProduct());
        typeproduct.setText(order.getStyle());
        size.setText(order.getSize());
        gender.setText(order.getGender());
        price.setText(String.valueOf(order.getPrice()));
        quantity.setText(String.valueOf(order.getQuantity()));
        age.setText(order.getAge());
        address.setText(order.getAddress());
        buyer.setText(order.getBuyer());

        try {
            String imagePathproduct = order.getImage(); // Lấy đường dẫn từ CSDL
            Image image = new Image(imagePathproduct);
            imageorder.setImage(image);
            imageorder.setPreserveRatio(true); // Đảm bảo tỷ lệ khung hình được bảo toàn
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // Xử lý hoặc ghi nhận ngoại lệ một cách thích hợp
        }

    }
}
