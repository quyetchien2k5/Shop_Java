package Controller;

import Model.ModelProduct;
import Model.Order;
import Model.Shop;
import View.Login;
import View.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import Model.ConnectSQL;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import View.HomePage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import static Model.ConnectSQL.getConnect;

public class ControllerHomePage {
    public static HomePage homepage;
    public AnchorPane homescene;
    public AnchorPane wellcomescene;
    public AnchorPane shopscene;
    public AnchorPane createshop;
    public ScrollPane menuproduct;
    public Label nameowner;
    public TextField nameshoptext;
    public TextField addressshoptext;
    public TextField phonenumbershoptext;
    public TextField emailshoptext;
    public Label erroraddresslabelshop;
    public Label errornamelabelshop;
    public Label erroremaillabelshop;
    public Label errorphonenumberlabelshop;
    public Label createshoppass;
    public TableView<Shop> tableviewshopt;
    public TableView tableviewshop;
    public TableColumn idshop;
    public TableColumn nameshop;
    public Label idinformationshop;
    public Label datestartinformationshop;
    public Label numberproductinformationshop;
    public Label ownerinformationshop;
    public Label phonenumberinformationshop;
    public Label addressinformationshop;
    public Label nameinformationshop;
    public Label emailinformationshop;
    public AnchorPane managershop;
    public TextField checkidshop;
    public Label errorlabelcheckidshop;
    public Label passlabelidcheckshop;
    public Label localdate;

    public int id = 0;
    public String name = null;
    public String address = null;
    public int phoneNumber = 0;
    public String email = null;
    public String owner = null;
    public int numberofproduct = 0;
    public String date = null;
    public static int eventclick;

    public AnchorPane updateinformationshop;
    public TextField updateaddress;
    public Label updateidshop;
    public TextField updateemail;
    public TextField updatenameshop;
    public TextField updatephonenumber;
    public Label updatenumberproduct;
    public Label updatenameowner;
    public Label updatedatestart;
    public Label labelupdate;
    public AnchorPane productscene;
    public Button choiceimagebutton;
    public ImageView imageproduct;
    public MenuButton choicestyle;
    public MenuButton choicegender;
    public MenuButton choicesize;
    public MenuButton choiceage;
    public TextField nameproductinput;
    public TextField priceproductinput;
    public Label ownerproductlabel;
    public TextField quantityproductinput;
    public TextField shopproductinput;
    public Label passerrorproduct;
    public Label errorproduct;

    public String imagePath;
    public AnchorPane menuaddbutton;
    public TableColumn idproduct;
    public TableColumn nameproduct;
    public TableColumn genderproduct;
    public TableColumn sizeproduct;
    public TableColumn nameshopproduct;
    public TableColumn priceproduct;
    public TableColumn styleproduct;
    public TableColumn quantityproduct;
    public TableView tabelviewproduct;
    public TextField nameproductinputupdate;
    public TextField priceproductinputupdate;
    public Label ownerproductlabelupdate;
    public TextField quantityproductinputupdate;
    public Button choiceimagebuttonupdate;
    public ImageView imageproductupdate;
    public MenuButton choicestyleupdate;
    public MenuButton choicegenderupdate;
    public MenuButton choicesizeupdate;
    public MenuButton choiceageupdate;
    public TextField shopproductinputupdate;

    public int getidproduct = 0;
    public String nameproductupdatevalue;
    public String sizeproductupdatevalue;
    public String genderporductupdatevalue;
    public String styleproductupdatevalue;
    public int quantityproductupdatevalue;
    public int priceproductupdatevalue;
    public String ageproductupdatevalue;
    public String nameshopproductupdatevalue;
    public String nameuserproductupdatevalue;
    public  String imagepath;
    public String nameshopcheck = null;
    public int numbercheck = 0;

    public Label errorchoiceproduct;
    public AnchorPane menuaddbuttonupdate;
    public Label passupdateproduct;
    public Label errorupdateproduct;
    public TableView tabelviewinshop;
    public TableColumn nameproductinshop;
    public TableColumn idproductinshop;
    public TableColumn priceproductinshop;
    public TableColumn quantityproductinshop;
    public TableColumn ageproduct;
    public GridPane scrollpaneproduct;
    public AnchorPane orderproductlist;
    public AnchorPane managershopbuttonacp;
    public AnchorPane createshopbuttonacp;
    public TextField searchproductinput;
    public AnchorPane accountscene;
    public AnchorPane cartscene;
    public TextField inputproductkey;
    public ScrollPane menucart;
    public Label nameuserupdate;
    public TextField phonenumberueserupdate;
    public TextField genderuserupdate;
    public TextField ageuserupdate;
    public TextField addressuserupdate;
    public Label emailuserupdate;
    public AnchorPane changepassworduser;
    public AnchorPane menuuserinformation;
    public AnchorPane deleteuseraccount;
    public Label passlabeluser;
    public ScrollPane ordermenu;
    private List<ModelProduct> currentProducts;
    public String adderess;

    //--------------------------------MENU-------------------------------
public void logout(ActionEvent actionEvent) {
    Login.stageLogin.show();
    HomePage.stageHomePage.close();
}
    public void setHomePage(HomePage homepage){
       this.homepage = homepage;
    }

    ControllerLogin controllerLogin = new ControllerLogin();

    public void onhome(ActionEvent actionEvent) throws SQLException {
        homescene.setVisible(true);
        shopscene.setVisible(false);
        productscene.setVisible(false);
        wellcomescene.setVisible(false);
        if(searchproductinput.getText().isEmpty() == true){
            loadProductDataindatabase();
        }else{
            loadProductData();
        }

        searchproductinput.setVisible(true);
        accountscene.setVisible(false);
        cartscene.setVisible(false);
    }
    public void onshop(ActionEvent actionEvent) {
        homescene.setVisible(false);
        shopscene.setVisible(true);
        productscene.setVisible(false);
        wellcomescene.setVisible(false);
        inputtable();
        searchproductinput.setVisible(false);
        accountscene.setVisible(false);
        cartscene.setVisible(false);
    }
    public void onproduct(ActionEvent actionEvent) {
        wellcomescene.setVisible(false);
        homescene.setVisible(false);
        shopscene.setVisible(false);
        productscene.setVisible(true);
        inputtableproduct();
        searchproductinput.setVisible(false);
        accountscene.setVisible(false);
        cartscene.setVisible(false);
    }
    public void oncartS(ActionEvent actionEvent) throws SQLException {
        cartscene.setVisible(true);
        wellcomescene.setVisible(false);
        homescene.setVisible(false);
        shopscene.setVisible(false);
        productscene.setVisible(false);
        searchproductinput.setVisible(false);
        accountscene.setVisible(false);
        loadCartDataindatabase();
    }

    public void onaccount(ActionEvent actionEvent) {
        accountscene.setVisible(true);
        wellcomescene.setVisible(false);
        homescene.setVisible(false);
        shopscene.setVisible(false);
        productscene.setVisible(false);
        searchproductinput.setVisible(false);
        cartscene.setVisible(false);
    }

    public void exithomepage(ActionEvent actionEvent) {
        System.exit(0);
    }
//--------------------------HOME------------------------------

//--------------------------------SHOP---------------------------------

public void deleteshop(MouseEvent mouseEvent) throws SQLException {
    Connection connection = ConnectSQL.getConnect();
    String namesh = getnameshop();
    deleteproductfromshop(namesh);
    String delete = "DELETE FROM shop WHERE idshop = ?";
    try(PreparedStatement preparedStatement = connection.prepareStatement(delete)){
        preparedStatement.setString(1, checkidshop.getText());
        int check = preparedStatement.executeUpdate();

    }catch (Exception e){
        e.printStackTrace();
    }
    inputtable();
    tabelviewinshop.setVisible(false);
}
    public void deleteproductfromshop(String nameshop) throws SQLException {
        Connection connection = ConnectSQL.getConnect();

        String delete = "DELETE FROM product WHERE shopname = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(delete)){
            preparedStatement.setString(1, nameshop);
            int check = preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        inputtable();
        tabelviewinshop.setVisible(false);
    }

    public void updateinformationshop(MouseEvent mouseEvent) {
        if(idcheckshop()){
            updateinformationshop.setVisible(true);
            orderproductlist.setVisible(false);
            passlabelidcheckshop.setText("id cửa hàng có tồn tại");
            errorlabelcheckidshop.setText(" ");
            getupdateinformation();
            tabelviewinshop.setVisible(false);
        }else{
            errorlabelcheckidshop.setText("id cửa hàng không tồn tại");
            passlabelidcheckshop.setText(" ");
            updateinformationshop.setVisible(false);
        }
    }

    public void resetupdate(ActionEvent actionEvent) {
        getupdateinformation();
    }

    public void updateshop(ActionEvent actionEvent) {
        updateinformationshop();
        inputtable();
    }

public boolean idcheckshop(){
    boolean recordExists = false;
    Connection connection = ConnectSQL.getConnect();
    String checkid = "SELECT idshop FROM shop WHERE idshop = ?";
    try(PreparedStatement preparedStatement = connection.prepareStatement(checkid)){
        preparedStatement.setInt(1, Integer.parseInt(checkidshop.getText()));

        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            // Nếu có bản ghi, có nghĩa là ID tồn tại
            System.out.println(recordExists);
            recordExists = resultSet.next();
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    return recordExists;
}

public void orderproductevent(MouseEvent mouseEvent) throws SQLException {
    if(idcheckshop()){
        orderproductlist.setVisible(true);
        managershop.setVisible(false);
        tableviewshop.setVisible(false);
        createshopbuttonacp.setVisible(false);
        managershopbuttonacp.setVisible(false);
        updateinformationshop.setVisible(false);
        tabelviewinshop.setVisible(false);
        //getInformationshop();
        loadorderDataindatabase();
        passlabelidcheckshop.setText("id cửa hàng có tồn tại");
        errorlabelcheckidshop.setText(" ");
    }else{
        errorlabelcheckidshop.setText("id cửa hàng không tồn tại");
        passlabelidcheckshop.setText(" ");
        orderproductlist.setVisible(false);
    }
}

public void getupdateinformation(){
    Connection connection = ConnectSQL.getConnect();
    String information = "SELECT idshop, nameshop, address,phoneNumber, email, numberofproduct, datestart, owner FROM shop WHERE idshop = ?";
    try(PreparedStatement preparedStatement = connection.prepareStatement(information)) {
        preparedStatement.setInt(1, Integer.parseInt(checkidshop.getText()));

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            id = resultSet.getInt("idshop");
            name = resultSet.getString("nameshop");
            address = resultSet.getString("address");
            phoneNumber = resultSet.getInt("phoneNumber");
            email = resultSet.getString("email");
            date = resultSet.getString("datestart");
            owner = resultSet.getString("owner");
        }
        updateidshop.setText(String.valueOf(id));
        updatenameshop.setText(name);
        updateaddress.setText(address);
        updatephonenumber.setText(String.valueOf(phoneNumber));
        updateemail.setText(email);
        checknumberofproduct();
        updatenumberproduct.setText(String.valueOf(numbercheck));
        updatedatestart.setText(date);
        updatenameowner.setText(owner);
    }catch (Exception e){
        e.printStackTrace();
    }
}

    public void updateinformationshop(){
        Connection connection = ConnectSQL.getConnect();
        String update = "UPDATE shop SET nameshop = ?, address = ?, phoneNumber = ?, email = ? WHERE idshop = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(update)){
            preparedStatement.setString(1, updatenameshop.getText());
            preparedStatement.setString(2, updateaddress.getText());
            preparedStatement.setString(3, updatephonenumber.getText());
            preparedStatement.setString(4, updateemail.getText());
            preparedStatement.setString(5, checkidshop.getText());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                labelupdate.setText("Đã cập nhật thông tin");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
public void createnewshop(MouseEvent mouseEvent) {
    if (nameshoptext.getText().isBlank() == true || addressshoptext.getText().isBlank() == true || phonenumbershoptext.getText().isBlank() == true || emailshoptext.getText().isBlank() == true) {
        if (nameshoptext.getText().isBlank() == true) {
            errornamelabelshop.setText("Nhập tên cửa hàng");
        } else {
            errornamelabelshop.setText(" ");
        }
        if (addressshoptext.getText().isBlank() == true) {
            erroraddresslabelshop.setText("Nhập địa chỉ cửa hàng");
        } else {
            erroraddresslabelshop.setText(" ");
        }
        if (phonenumbershoptext.getText().isBlank() == true) {
            errorphonenumberlabelshop.setText("Nhập số điện thoại cửa hàng");
        } else {
            errorphonenumberlabelshop.setText(" ");
        }
        if (emailshoptext.getText().isBlank() == true) {
            erroremaillabelshop.setText(" Nhập email cửa hàng");
        } else {
            erroremaillabelshop.setText(" ");
        }
    } else {
        errornamelabelshop.setText(" ");
        errorphonenumberlabelshop.setText(" ");
        erroraddresslabelshop.setText(" ");
        erroremaillabelshop.setText(" ");
        try {
            ConnectSQL.inputshop(nameshoptext.getText(), addressshoptext.getText(), Integer.parseInt(phonenumbershoptext.getText()), emailshoptext.getText(), nameowner.getText());
            createshoppass.setText(" Tạo cửa hàng thành công");
        }catch (Exception e){
            errorphonenumberlabelshop.setText("Số điện thoại không được chứa kí tự");
        }
    }
    inputtable();
}
    public void showproduct(MouseEvent mouseEvent) {
        if(idcheckshop()) {
            orderproductlist.setVisible(false);
            updateinformationshop.setVisible(false);
            tabelviewinshop.setVisible(true);
            inputtableproductinshop();
            errorlabelcheckidshop.setText("");
        }else{
            errorlabelcheckidshop.setText("id cửa hàng không tồn tại");
            passlabelidcheckshop.setText(" ");
            tabelviewinshop.setVisible(false);
        }
    }
    public void exit(ActionEvent actionEvent) {
        updateinformationshop.setVisible(false);
    }

    public void resetimformationshop(MouseEvent mouseEvent) {
        nameshoptext.setText(" ");
        addressshoptext.setText(" ");
        phonenumbershoptext.setText(" ");
        emailshoptext.setText(" ");
    }
public void inputtable(){
    try {
        idshop.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameshop.setCellValueFactory(new PropertyValueFactory<>("name"));
        // Lấy danh sách cửa hàng từ CSDL và hiển thị trong TableView
        ObservableList<Shop> shopList = ConnectSQL.getidandnameshop();
        tableviewshop.setItems(shopList);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void managershoplabel(MouseEvent mouseEvent) {
    managershop.setVisible(true);
    createshop.setVisible(false);
}

    public void createshoplabel(MouseEvent mouseEvent) {
        ControllerLogin controllerLogin1 = new ControllerLogin();
        createshop.setVisible(true);
        managershop.setVisible(false);

        nameowner.setText(ControllerLogin.nameow);
        localdate.setText(getLocaldate());
    }

    public String getLocaldate(){
        LocalDateTime thoiGianThuc = LocalDateTime.now();
        // Định dạng thời gian theo một định dạng cụ thể
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Chuyển đổi thời gian thành chuỗi theo định dạng
        String chuoiThoiGian = thoiGianThuc.format(dinhDang);

        return chuoiThoiGian;
    }

    public String getnameshop() {
        Connection connection = ConnectSQL.getConnect();

        String show = "SELECT nameshop FROM shop WHERE idshop = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(show)) {
            int idcheck1 = Integer.parseInt(checkidshop.getText());
            preparedStatement.setInt(1, idcheck1);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Nếu có ít nhất một dòng kết quả, lấy giá trị từ cột "nameshop"
                nameshopcheck = resultSet.getString("nameshop");
            } else {
                // Nếu không có dòng kết quả, có thể xử lý tùy thuộc vào yêu cầu của bạn
                nameshopcheck = "Shop không tồn tại"; // Hoặc bất kỳ thông báo nào bạn muốn
            }
            System.out.println(nameshopcheck);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nameshopcheck;

    }
    public void inputtableproductinshop() {
        try {
            // thuộc tính bên model
            idproductinshop.setCellValueFactory(new PropertyValueFactory<>("IdProduct"));
            nameproductinshop.setCellValueFactory(new PropertyValueFactory<>("NameProduct"));
            priceproductinshop.setCellValueFactory(new PropertyValueFactory<>("Price"));
            quantityproductinshop.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

            // Lấy danh sách sản phẩm từ CSDL và hiển thị trong TableView
            ObservableList<ModelProduct> modelProductList1 = getinformationproductinshop();
            tabelviewinshop.setItems(modelProductList1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exittabelcreateshop(MouseEvent mouseEvent) {
        createshop.setVisible(false);
    }
    public int checknameshop() {
        Connection connection = ConnectSQL.getConnect();
        String checkid = "SELECT COUNT(*) FROM shop WHERE nameshop = ? AND owner = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(checkid)) {
            preparedStatement.setString(1, shopproductinput.getText());
            preparedStatement.setString(2, ownerproductlabel.getText());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1); // Lấy giá trị số lượng hàng
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

   //--------------------------------PRODUCT------------------------------
   public List<ModelProduct> searchProducts(String keyword) throws SQLException {
       // Thực hiện tìm kiếm trong danh sách sản phẩm dựa trên từ khóa
       List<ModelProduct> searchResults = new ArrayList<>();

       for (ModelProduct product : getProducts()) {
           // Kiểm tra nếu tên sản phẩm chứa từ khóa tìm kiếm (kiểm tra không phân biệt chữ hoa/chữ thường)
           if (product.getNameProduct().toLowerCase().contains(keyword.toLowerCase())) {
               searchResults.add(product);
           }
       }

       return searchResults;
   }

    public void inputkey(KeyEvent keyEvent) throws SQLException {
        String keyword = searchproductinput.getText().toLowerCase();
        List<ModelProduct> searchResults = new ArrayList<>();

        // Thực hiện tìm kiếm trong danh sách sản phẩm dựa trên từ khóa
        for (ModelProduct product : getProducts()) {
            if (product.getNameProduct().toLowerCase().contains(keyword)) {
                searchResults.add(product);
            }
        }

        currentProducts = searchResults;
        loadProductData();
    }
    public void loadProductDataindatabase() throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/product.fxml"));

            List<ModelProduct> modelProducts = getProducts();

            TilePane tilePane = new TilePane();
            tilePane.setPrefColumns(5);  // Đặt số cột
            tilePane.setHgap(10);  // Đặt khoảng cách ngang giữa các nút
            tilePane.setVgap(20);  // Đặt khoảng cách dọc giữa các nút

            for (ModelProduct productView : modelProducts) {
                // Tải một phiên bản mới của view FXML và lấy controller của nó
                Node productNode = loader.load();
                ControllerProduct productController = loader.getController();

                // Thiết lập dữ liệu cho controller
                productController.setProductData(productView);

                // Thêm node đã tải vào TilePane
                tilePane.getChildren().add(productNode);

                // Tạo một loader mới để sử dụng cho sản phẩm tiếp theo
                loader = new FXMLLoader(getClass().getResource("/View/product.fxml"));
            }

            ScrollPane scrollPane = new ScrollPane(tilePane);
            menuproduct.setContent(scrollPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadCartDataindatabase() throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/cart.fxml"));

            List<ModelProduct> modelProductcart = getProductcarts();

            TilePane tilePane = new TilePane();
            tilePane.setPrefColumns(1);  // Đặt số cột
            tilePane.setHgap(10);  // Đặt khoảng cách ngang giữa các nút
            tilePane.setVgap(20);  // Đặt khoảng cách dọc giữa các nút

            for (ModelProduct productView : modelProductcart) {
                // Tải một phiên bản mới của view FXML và lấy controller của nó
                Node productNode = loader.load();
                ControllerCart productcart = loader.getController();

                // Thiết lập dữ liệu cho controller
                productcart.setProductData(productView);

                // Thêm node đã tải vào TilePane
                tilePane.getChildren().add(productNode);

                // Tạo một loader mới để sử dụng cho sản phẩm tiếp theo
                loader = new FXMLLoader(getClass().getResource("/View/cart.fxml"));
            }
            ScrollPane scrollPane = new ScrollPane(tilePane);
            menucart.setContent(scrollPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadorderDataindatabase() throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/order.fxml"));
                ControllerOrder controllerOrder = new ControllerOrder();
            List<Order> modelProductorder = getProductorder();

            TilePane tilePane = new TilePane();
            tilePane.setPrefColumns(1);  // Đặt số cột
            tilePane.setHgap(10);  // Đặt khoảng cách ngang giữa các nút
            tilePane.setVgap(20);  // Đặt khoảng cách dọc giữa các nút

            for (Order productView : modelProductorder) {
                // Tải một phiên bản mới của view FXML và lấy controller của nó
                Node productNode = loader.load();
                ControllerOrder productorder = loader.getController();

                // Thiết lập dữ liệu cho controller
                productorder.setProductData(productView);

                // Thêm node đã tải vào TilePane
                tilePane.getChildren().add(productNode);

                // Tạo một loader mới để sử dụng cho sản phẩm tiếp theo
                loader = new FXMLLoader(getClass().getResource("/View/order.fxml"));
            }
            ScrollPane scrollPane = new ScrollPane(tilePane);
            ordermenu.setContent(scrollPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadProductData() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/product.fxml"));

            TilePane tilePane = new TilePane();
            tilePane.setPrefColumns(5);
            tilePane.setHgap(10);
            tilePane.setVgap(20);

            for (ModelProduct productView : currentProducts) {
                Node productNode = loader.load();
                ControllerProduct productController = loader.getController();
                productController.setProductData(productView);
                tilePane.getChildren().add(productNode);

                // Tạo một loader mới để sử dụng cho sản phẩm tiếp theo
                loader = new FXMLLoader(getClass().getResource("/View/product.fxml"));
            }

            ScrollPane scrollPane = new ScrollPane(tilePane);
            menuproduct.setContent(scrollPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void choiceImage(ActionEvent actionEvent) throws SQLException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null && selectedFile.exists()) {
            imagePath = selectedFile.toURI().toString();
            // Lưu đường dẫn hình ảnh vào biến toàn cục
            imageproduct.setPreserveRatio(true);
            imageproduct.setImage(new Image(imagePath));
        }
    }

    public void addproduct(ActionEvent actionEvent) throws SQLException {
        if (checknameshop() > 0) {
            if (imagePath != null && !imagePath.isEmpty()) {
                insertProduct(imagePath);
                passerrorproduct.setText("Đã thêm sản phẩm");
                inputtableproduct();
                errorproduct.setText("");
                loadProductData();
            } else {
                errorproduct.setText("Không chọn ảnh hoặc có lỗi khi chọn ảnh");
                passerrorproduct.setText("");
            }
        } else {
            errorproduct.setText("Sai thông tin cửa hàng hoặc ảnh");
            passerrorproduct.setText("");
        }
    }


   public void updateinformationproduct(ActionEvent actionEvent) throws SQLException {

       if(getidproduct == 0){
           errorchoiceproduct.setText("Chọn sản phẩm cần cập nhập từ bảng");
       }else{
           menuaddbuttonupdate.setVisible(true);
           menuaddbutton.setVisible(false);
           getproduct();
           errorchoiceproduct.setText("");
       }
   }
    public void stylequanupdate(ActionEvent actionEvent) {
        choicestyleupdate.setText("Quần");
    }

    public void slyteaoupdate(ActionEvent actionEvent) {
        choicestyleupdate.setText("Áo");
    }

    public void slyteboupdate(ActionEvent actionEvent) {
        choicestyleupdate.setText("Đồ bộ");
    }

    public void slytephukienupdate(ActionEvent actionEvent) {
        choicestyleupdate.setText("Phụ kiện");
    }

    public void stylemuupdate(ActionEvent actionEvent) {
        choicestyleupdate.setText("Mũ");
    }

    public void stylegiayupdate(ActionEvent actionEvent) {
        choicestyleupdate.setText("Giày dép");
    }

    public void stylevayupdate(ActionEvent actionEvent) {
        choicestyleupdate.setText("Váy");
    }

    public void gendernamupdate(ActionEvent actionEvent) {
        choicegenderupdate.setText("Nam");
    }

    public void gendernuupdate(ActionEvent actionEvent) {
        choicegenderupdate.setText("Nữ");
    }

    public void genderkhacupdate(ActionEvent actionEvent) {
        choicegenderupdate.setText("Khác");
    }

    public void sizesupdate(ActionEvent actionEvent) {
        choicesizeupdate.setText("S");
    }

    public void sizemupdate(ActionEvent actionEvent) {
        choicesizeupdate.setText("M");
    }

    public void sizelupdate(ActionEvent actionEvent) {
        choicesizeupdate.setText("L");
    }

    public void sizexlupdate(ActionEvent actionEvent) {
        choicesizeupdate.setText("XL");
    }

    public void age1update(ActionEvent actionEvent) {
        choiceageupdate.setText("0-1 tuổi");
    }

    public void age5update(ActionEvent actionEvent) {
        choiceageupdate.setText("1-5 tuổi");
    }

    public void age10update(ActionEvent actionEvent) {
        choiceageupdate.setText("5-10 tuổi");
    }

    public void agetren10update(ActionEvent actionEvent) {
        choiceageupdate.setText("trên 10 tuổi");
    }

    public void updateproductinformation(String imagePathupdate) throws Exception{
        Connection connection = ConnectSQL.getConnect();

        String update = "UPDATE product SET nameproduct = ?, style = ?, gender = ?, size = ?, shopname = ?, image = ?, quantity = ?, price = ?, age = ? WHERE idproduct = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)){
            preparedStatement.setString(1, nameproductinputupdate.getText());
            preparedStatement.setString(2, choicestyleupdate.getText());
            preparedStatement.setString(3, choicegenderupdate.getText());
            preparedStatement.setString(4, choicesizeupdate.getText());
            preparedStatement.setString(5, shopproductinputupdate.getText());

            preparedStatement.setString(6, imagePath);
            preparedStatement.setString(7, quantityproductinputupdate.getText());
            preparedStatement.setString(8, priceproductinputupdate.getText());
            preparedStatement.setString(9, choiceageupdate.getText());
            preparedStatement.setString(10, String.valueOf(getidproduct));

            int resultSet = preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void insertproductbutton(ActionEvent actionEvent) {
        System.out.println(ControllerLogin.nameow);
        ownerproductlabel.setText(ControllerLogin.nameow);
        menuaddbutton.setVisible(true);
        menuaddbuttonupdate.setVisible(false);
    }

    public void stylequan(ActionEvent actionEvent) {
        choicestyle.setText("Quần");
    }

    public void slyteao(ActionEvent actionEvent) {
        choicestyle.setText("Áo");
    }

    public void slytebo(ActionEvent actionEvent) {
        choicestyle.setText("Đồ bộ");
    }

    public void slytephukien(ActionEvent actionEvent) {
        choicestyle.setText("Phụ kiện");
    }

    public void stylemu(ActionEvent actionEvent) {
        choicestyle.setText("Mũ");
    }

    public void stylegiay(ActionEvent actionEvent) {
        choicestyle.setText("Giày dép");
    }

    public void stylevay(ActionEvent actionEvent) {
        choicestyle.setText("Váy");
    }

    public void gendernam(ActionEvent actionEvent) {
        choicegender.setText("Nam");
    }

    public void gendernu(ActionEvent actionEvent) {
        choicegender.setText("Nữ");
    }

    public void genderkhac(ActionEvent actionEvent) {
        choicegender.setText("Khác");
    }

    public void sizes(ActionEvent actionEvent) {
        choicesize.setText("S");
    }

    public void sizem(ActionEvent actionEvent) {
        choicesize.setText("M");
    }

    public void sizel(ActionEvent actionEvent) {
        choicesize.setText("L");
    }

    public void sizexl(ActionEvent actionEvent) {
        choicesize.setText("XL");
    }

    public void age1(ActionEvent actionEvent) {
        choiceage.setText("0-1 tuổi");
    }

    public void age5(ActionEvent actionEvent) {
        choiceage.setText("1-5 tuổi");
    }

    public void age10(ActionEvent actionEvent) {
        choiceage.setText("5-10 tuổi");
    }

    public void agetren10(ActionEvent actionEvent) {
        choiceage.setText("trên 10 tuổi");
    }

    public void resetinormationproduct(ActionEvent actionEvent) {
        choicestyle.setText("Loại");
        choicegender.setText("Giới tính");
        choiceage.setText("Tuổi");
        choicesize.setText("Kích thước");
        quantityproductinput.setText("");
        nameproductinput.setText("");
        priceproductinput.setText("");
        shopproductinput.setText("");
        imageproduct.setImage(null);
        passerrorproduct.setText("");
    }

    public void exitproduct(ActionEvent actionEvent) {
        menuaddbutton.setVisible(false);
    }

    public void getinformationproductfromtabe(MouseEvent mouseEvent) {
        eventclick = mouseEvent.getClickCount();
        if (eventclick > 0) {
            // Lấy thông tin về hàng được chọn
            ModelProduct selectedModelProduct = (ModelProduct) tabelviewproduct.getSelectionModel().getSelectedItem();
            if (selectedModelProduct != null) {
                // lấy thông tin
                getidproduct = selectedModelProduct.getIdProduct();

            }
        }
    }

    public void choiceImageupdate(ActionEvent actionEvent) throws Exception {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null && selectedFile.exists()) {
            imagePath = selectedFile.toURI().toString();
            // Lưu đường dẫn hình ảnh vào biến toàn cục
            imageproductupdate.setPreserveRatio(true);
            imageproductupdate.setImage(new Image(imagePath));
        }
    }
    public void delectproduct(MouseEvent mouseEvent) {
        Connection connection = ConnectSQL.getConnect();

        String delete = "DELETE FROM product WHERE idproduct = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(delete)){
            preparedStatement.setInt(1, getidproduct);
            int check1 = preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        inputtableproduct();
    }



    public List<ModelProduct> getProducts() throws SQLException {
        ControllerProduct controllerProduct = new ControllerProduct();
        return controllerProduct.getProducts();
    }
    public List<ModelProduct> getProductcarts() throws SQLException {
        ControllerCart controllercart = new ControllerCart();
        return controllercart.getProducts();
    }
    public List<Order> getProductorder() throws SQLException {
        ControllerOrder controllerorder = new ControllerOrder();
        return controllerorder.getorder();
    }

    public void updateproduct(ActionEvent actionEvent) throws SQLException {
        try {
            Image image = imageproductupdate.getImage();
            imagePath = image.getUrl();
            updateproductinformation(imagePath);
            passupdateproduct.setText("Đã cập nhập");
            errorupdateproduct.setText("");
            inputtableproduct();
        }catch (Exception e){
            e.printStackTrace();
            errorupdateproduct.setText("Không thành công");
            passupdateproduct.setText("");
            loadProductData();
        }
    }

    public void resetinormationproductupdate(ActionEvent actionEvent) throws SQLException {
        getproduct();
    }

    public void exitproductupdate(ActionEvent actionEvent) {
        menuaddbuttonupdate.setVisible(false);
    }

    public void getproduct() throws SQLException {
       Connection connection = ConnectSQL.getConnect();

       String getproduct = "SELECT nameproduct, style, gender, size, shopname, image, username, price, quantity, age FROM product WHERE idproduct = ?";
       try (PreparedStatement preparedStatement = connection.prepareStatement(getproduct)){
           preparedStatement.setString(1, String.valueOf(getidproduct));
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) {
               nameproductupdatevalue = resultSet.getString("nameproduct");
               styleproductupdatevalue= resultSet.getString("style");
               genderporductupdatevalue = resultSet.getString("gender");
               sizeproductupdatevalue = resultSet.getString("size");
               ageproductupdatevalue = resultSet.getString("age");
               quantityproductupdatevalue = resultSet.getInt("quantity");
               priceproductupdatevalue = resultSet.getInt("price");
               nameshopproductupdatevalue = resultSet.getString("shopname");
               nameuserproductupdatevalue = resultSet.getString("username");
               imagepath = resultSet.getString("image");
           }
           nameproductinputupdate.setText(nameproductupdatevalue);
           choicestyleupdate.setText(styleproductupdatevalue);
           choicegenderupdate.setText(genderporductupdatevalue);
           choicesizeupdate.setText(sizeproductupdatevalue);
           choiceageupdate.setText(ageproductupdatevalue);
           quantityproductinputupdate.setText(String.valueOf(quantityproductupdatevalue));
           priceproductinputupdate.setText(String.valueOf(priceproductupdatevalue));
           shopproductinputupdate.setText(nameshopproductupdatevalue);
           ownerproductlabelupdate.setText(nameuserproductupdatevalue);
           Image image = new Image(new File(imagepath).toURI().toString());
           imageproductupdate.setImage(new Image(imagepath));


       }catch (Exception e){
           e.printStackTrace();
       }
   }



    public void inputtableproduct() {
        try {
            // thuộc tính bên model
            idproduct.setCellValueFactory(new PropertyValueFactory<>("IdProduct"));
            nameproduct.setCellValueFactory(new PropertyValueFactory<>("NameProduct"));
            sizeproduct.setCellValueFactory(new PropertyValueFactory<>("Size"));
            genderproduct.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            priceproduct.setCellValueFactory(new PropertyValueFactory<>("Price"));
            styleproduct.setCellValueFactory(new PropertyValueFactory<>("Style"));
            nameshopproduct.setCellValueFactory(new PropertyValueFactory<>("Nameshop"));
            quantityproduct.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
            ageproduct.setCellValueFactory(new PropertyValueFactory<>("Age"));

            // Lấy danh sách sản phẩm từ CSDL và hiển thị trong TableView
            ObservableList<ModelProduct> modelProductList = ConnectSQL.getinformationproduct();
            tabelviewproduct.setItems(modelProductList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ModelProduct> getinformationproductinshop() {
        ObservableList productList1 = FXCollections.observableArrayList();
        Connection connection = getConnect();

        String selectQuery = "SELECT idproduct, nameproduct, price, quantity FROM product WHERE shopname = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            String shopName = getnameshop();
            if (shopName != null && !shopName.isEmpty()) {
                preparedStatement.setString(1, shopName);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    // cột trong csdl
                    int id = resultSet.getInt("idproduct");
                    String name = resultSet.getString("nameproduct");
                    int price = resultSet.getInt("price");
                    int quantity = resultSet.getInt("quantity");

                    productList1.add(new ModelProduct(id, name, price, quantity));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList1;
    }

    public int checknumberofproduct() throws SQLException {
        Connection connection = ConnectSQL.getConnect();

        String show = "SELECT COUNT(idproduct) FROM product WHERE shopname = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(show) ){
            preparedStatement.setString(1, getnameshop());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Lấy dữ liệu từ ResultSet
                numbercheck = resultSet.getInt(1);
            }
        }
        return numbercheck;
    }
    public void inputproductkeylistens(KeyEvent keyEvent) {
        String searchKey = inputproductkey.getText();

        if (!searchKey.isEmpty() && inputproductkey.getText() != null) {
            // Gọi phương thức updateTableView để cập nhật TableView dựa trên khóa tìm kiếm
            updateTableView(searchKey);
        }else{
            // Xử lý trường hợp khi searchKey rỗng
            // (ví dụ: hiển thị toàn bộ danh sách sản phẩm)
            System.out.println("null");
            inputtableproduct();
        }
    }
    public ObservableList<ModelProduct> getinformationproduct() {
        ObservableList productList2 = FXCollections.observableArrayList();
        Connection connection = getConnect();

        String selectQuery = "SELECT idproduct, nameproduct, style, gender, size, shopname, price, quantity, age FROM product WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            String nameuser = ControllerLogin.nameow;
            if (nameuser != null && !nameuser.isEmpty()) {
                preparedStatement.setString(1, nameuser);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    // cột trong csdl
                    int id2 = resultSet.getInt("idproduct");
                    String name2 = resultSet.getString("nameproduct");
                    int price2 = resultSet.getInt("price");
                    int quantity2 = resultSet.getInt("quantity");
                    String style2 = resultSet.getString("style");
                    String shopname2 = resultSet.getString("shopname");
                    String gender2 = resultSet.getString("gender");
                    String size2 = resultSet.getString("size");
                    String age2 = resultSet.getString("age");

                    productList2.add(new ModelProduct(id2, name2, gender2, age2, size2, style2, shopname2, price2, quantity2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList2;
    }

    public void updateTableView(String searchKey) {
        try {
            // Lấy danh sách sản phẩm từ CSDL và hiển thị trong TableView
            ObservableList<ModelProduct> modelProductList = getinformationproduct();

            // Lọc sản phẩm dựa trên khóa tìm kiếm
            if (searchKey != null && !searchKey.isEmpty()) {
                modelProductList = modelProductList.stream()
                        .filter(product -> product.getNameProduct().toLowerCase().contains(searchKey.toLowerCase()))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
            }

            // Thiết lập cột của TableView
            idproduct.setCellValueFactory(new PropertyValueFactory<>("IdProduct"));
            nameproduct.setCellValueFactory(new PropertyValueFactory<>("NameProduct"));
            sizeproduct.setCellValueFactory(new PropertyValueFactory<>("Size"));
            genderproduct.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            priceproduct.setCellValueFactory(new PropertyValueFactory<>("Price"));
            styleproduct.setCellValueFactory(new PropertyValueFactory<>("Style"));
            nameshopproduct.setCellValueFactory(new PropertyValueFactory<>("Nameshop"));
            quantityproduct.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
            ageproduct.setCellValueFactory(new PropertyValueFactory<>("Age"));

            // Đặt dữ liệu vào TableView
            tabelviewproduct.setItems(modelProductList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void insertProduct(String imagePath) throws SQLException {
        Connection connection = ConnectSQL.getConnect();

        // Kiểm tra xem tên cửa hàng đã tồn tại hay chưa
        if (checknameshop() > 0) {
            // Tên cửa hàng chưa tồn tại, thêm sản phẩm vào cơ sở dữ liệu
            String addProduct = "INSERT INTO product(nameproduct, style, gender, size, shopname, image, username, price, quantity, age) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(addProduct)) {
                preparedStatement.setString(1, nameproductinput.getText());
                preparedStatement.setString(2, choicestyle.getText());
                preparedStatement.setString(3, choicegender.getText());
                preparedStatement.setString(4, choicesize.getText());
                preparedStatement.setString(5, shopproductinput.getText());

                // Lưu đường dẫn hình ảnh thay vì dữ liệu hình ảnh
                preparedStatement.setString(6, imagePath);

                System.out.println(ownerproductlabel.getText());
                preparedStatement.setString(7, ownerproductlabel.getText());
                preparedStatement.setInt(8, Integer.parseInt(priceproductinput.getText()));
                preparedStatement.setInt(9, Integer.parseInt(quantityproductinput.getText()));
                preparedStatement.setString(10, choiceage.getText());

                int check = preparedStatement.executeUpdate();
            }
        }
    }
    public void oninformationuser(MouseEvent mouseEvent) {
        menuuserinformation.setVisible(true);
        getuserinformation();
        deleteuseraccount.setVisible(false);
        changepassworduser.setVisible(false);
    }
    public void repeart(ActionEvent actionEvent) {
        getuserinformation();
    }
    public void exitmenuuser(ActionEvent actionEvent) {
        menuuserinformation.setVisible(false);
        deleteuseraccount.setVisible(true);
        changepassworduser.setVisible(true);
    }

//--------------------------------------ACCOUNT---------------------------
public void updateUserInfo() {
    Connection connection = getConnect();
    String updateQuery = "UPDATE user SET name=?, phoneNumber=?, email=?, gender=?, age=?, address=? WHERE name=?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
        preparedStatement.setString(1, nameuserupdate.getText());
        preparedStatement.setString(2, phonenumberueserupdate.getText());
        preparedStatement.setString(3, emailuserupdate.getText());
        preparedStatement.setString(4, genderuserupdate.getText());
        preparedStatement.setString(5, ageuserupdate.getText());
        preparedStatement.setString(6, addressuserupdate.getText());
        preparedStatement.setString(7, ControllerLogin.nameow); // Đặc điểm để xác định người dùng cần cập nhật

        int rowsUpdated = preparedStatement.executeUpdate();


    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void getuserinformation() {
    Connection connection = getConnect();
    String selectQuery = "SELECT * FROM user WHERE name = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
        preparedStatement.setString(1, ControllerLogin.nameow);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Di chuyển con trỏ đến dòng đầu tiên
        if (resultSet.next()) {
            // Lấy dữ liệu từ ResultSet
            nameuserupdate.setText(resultSet.getString("name"));
            phonenumberueserupdate.setText(resultSet.getString("phoneNumber"));
            emailuserupdate.setText(resultSet.getString("email"));
            genderuserupdate.setText(resultSet.getString("gender"));
            ageuserupdate.setText(resultSet.getString("age"));
            addressuserupdate.setText(resultSet.getString("address"));
        } else {
            // Xử lý trường hợp không tìm thấy người dùng với tên đã chỉ định
            // Ví dụ: hiển thị thông báo lỗi hoặc xử lý phù hợp với ứng dụng của bạn
            System.out.println("Không tìm thấy người dùng với tên đã chỉ định.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public void updateuserinformation(ActionEvent actionEvent) throws SQLException {
            updateUserInfo();
            passlabeluser.setText("Đã cập nhập thông tin");

    }

//------------------------------------CART-----------------------------


//-----------------------------------ORDER-------------------------------

    public void exitorderproduct(MouseEvent mouseEvent) {
        orderproductlist.setVisible(false);
        managershop.setVisible(true);
        tableviewshop.setVisible(true);
        createshopbuttonacp.setVisible(true);
        managershopbuttonacp.setVisible(true);
    }

}