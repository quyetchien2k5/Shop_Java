package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import Controller.ControllerHomePage;
import Controller.ControllerLogin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static java.sql.DriverManager.getConnection;

public class ConnectSQL {

    public static Connection getConnect() {
        Connection connect = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            String url = "jdbc:mySQL://localhost:3306/managerproduct";
            String username = "root";
            String password = "";
            connect = getConnection(url, username, password);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return connect;
    }

    public static String hashPassword(String password) {
        try {
            // Sử dụng thuật toán SHA-256
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Chuyển đổi mật khẩu thành mảng bytes
            byte[] passwordBytes = password.getBytes();

            // Mã hóa mảng bytes
            byte[] hashedBytes = messageDigest.digest(passwordBytes);

            // Chuyển đổi mảng bytes đã mã hóa thành dạng hex
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            // Trả về chuỗi hex đại diện cho mật khẩu đã mã hóa
            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            // Xử lý nếu thuật toán không được hỗ trợ
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void inputuser(String name, String phoneNumber, String email, String password) {
        try {
           Connection connection = getConnect();
            String insertUser = "INSERT INTO user(name, phoneNumber, email, password) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertUser)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, phoneNumber);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, hashPassword(password));

                int rowsAffected = preparedStatement.executeUpdate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void inputshop(String name, String address, int phoneNumber, String email, String nameowner) {
       ControllerHomePage controllerHomePage = new ControllerHomePage();
        try {
            Connection connection = getConnect();
            String insertshop = "INSERT INTO shop(nameshop, address, phoneNumber, email, owner, datestart) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertshop)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, address);
                preparedStatement.setInt(3, phoneNumber);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, nameowner);
                preparedStatement.setString(6, controllerHomePage.getLocaldate());

                int testshop = preparedStatement.executeUpdate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Shop> getidandnameshop(){
        ObservableList<Shop> shopList = FXCollections.observableArrayList();
        Connection connection = getConnect();

            String selectQuery = "SELECT idshop, nameshop FROM shop WHERE owner = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, ControllerLogin.nameow);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("idshop");
                    String name = resultSet.getString("nameshop");
                    shopList.add(new Shop(id, name));
                }

        } catch (Exception e) {
                e.printStackTrace();
            }
        return shopList;
    }

public static ObservableList<ModelProduct> getinformationproduct(){
        ObservableList productList = FXCollections.observableArrayList();
    Connection connection = getConnect();

    String selectQuery = "SELECT idproduct, nameproduct, style, gender, size, shopname, price, quantity, age FROM product WHERE username = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
        preparedStatement.setString(1, ControllerLogin.nameow);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            // cột trong csdl
            int id = resultSet.getInt("idproduct");
            String name = resultSet.getString("nameproduct");
            String age = resultSet.getString("age");
            String gender = resultSet.getString("gender");
            String size = resultSet.getString("size");
            String style = resultSet.getString("style");
            String nameshop = resultSet.getString("shopname");
            int price = resultSet.getInt("price");
            int quantity = resultSet.getInt("quantity");

            productList.add(new ModelProduct(id, name, gender, size, style, nameshop, price, quantity, age));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
        return productList;
}
    public static void closeconnection() {
        try {
            getConnect().close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}