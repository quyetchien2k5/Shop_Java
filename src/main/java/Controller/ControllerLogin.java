package Controller;
import View.HomePage;
import View.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import View.Login;
import Model.ConnectSQL;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Model.ConnectSQL.hashPassword;

public class ControllerLogin {

    //register
    public static Login login;
    public Button SigninButton;
    public Button CloseButton;
    public Button RegisterButton;
    public Label nameerrorlabel;
    public Label phoneerrorlabel;
    public Label emailerrorlabel;
    public Label passworderrorlabel;
    public Label RegisterLabel;
    public Label RegisterPass;
    public ImageView Background_Signin;
    public ImageView Logo;
    public ImageView KeyRegister;
    public ImageView EmailRegister;
    public ImageView PhoneRegister;
    public ImageView NameRegister;
    public TextField NameTextRegister;
    public TextField PhoneTextRegister;
    public TextField EmailTextRegister;
    public PasswordField PasswordTextRegister;



    //signing
    public ImageView BackgroundLogin;
    public ImageView LogoLogin;
    public  TextField EmailTextSignin;
    public  PasswordField PasswordTextSignin;
    public ImageView KeySignin;
    public ImageView EmailSignin;
    public Label emailsigingerror;
    public Label passwordsigningerror;
    public static String nameow;

    HomePage homePage = new HomePage();
    ConnectSQL connectSQL = new ConnectSQL();
    public void setLogin(Login login) {
        this.login = login;
    }

    @FXML
    private void signinevent(ActionEvent e) throws SQLException {
        if(EmailTextSignin.getText().isBlank() == true || PasswordTextSignin.getText().isBlank() == true){
            if(EmailTextSignin.getText().isBlank() == true){
                emailsigingerror.setText("Vui lòng nhập email");
            }else{
                emailsigingerror.setText(" ");
            }
            if(PasswordTextSignin.getText().isBlank() == true){
                passwordsigningerror.setText("Vui lòng nhập mật khẩu");
            }else{
                passwordsigningerror.setText(" ");
            }
        }else{
            emailsigingerror.setText(" ");
            passwordsigningerror.setText(" ");
          verify();
        }
    }

    @FXML
    public void registerevent(ActionEvent actionEvent) throws SQLException {
        if(NameTextRegister.getText().isBlank() == true || PhoneTextRegister.getText().isBlank() == true || EmailTextRegister.getText().isBlank() == true ||PasswordTextRegister.getText().isBlank() == true) {
            if (NameTextRegister.getText().isBlank() == true){
                nameerrorlabel.setText("Vui lòng nhập tên");
            }else{
                nameerrorlabel.setText(" ");
            }

           if(PhoneTextRegister.getText().isBlank() == true){
               phoneerrorlabel.setText("Vui lòng nhập số điện thoại");
           }else if(PasswordTextRegister.getText().length() < 10){
               phoneerrorlabel.setText("Số điện thoại có 10 số");
           }else{
               phoneerrorlabel.setText(" ");
           }

           if(EmailTextRegister.getText().isBlank() == true){
               emailerrorlabel.setText("Vui lòng nhập email");
           }else{
               emailerrorlabel.setText(" ");
           }
           if(PasswordTextRegister.getText().isBlank() == true){
               passworderrorlabel.setText("Vui lòng nhập mật khẩu");
           }else{
                   passworderrorlabel.setText(" ");
           }

        }else{
            if (!isValidEmail(EmailTextRegister.getText())) {
                emailerrorlabel.setText("Định dạng email không hợp lệ");
                emailerrorlabel.setVisible(true);
            } else if (emailExists(EmailTextRegister.getText())) {
                emailerrorlabel.setText("Email đã được sử dụng");
                emailerrorlabel.setVisible(true);
            } else if (PasswordTextRegister.getText().length() < 8) {
                passworderrorlabel.setText("Mật khẩu ít nhất 8 kí tự");
            } else if (userExists(NameTextRegister.getText())) {
                nameerrorlabel.setText("Tên đã được sử dụng");
            } else {
                    passworderrorlabel.setText(" ");
                    // Đăng ký người dùng vào cơ sở dữ liệu
                    ConnectSQL.inputuser(NameTextRegister.getText(), PhoneTextRegister.getText(),
                            EmailTextRegister.getText(), PasswordTextRegister.getText());
                    RegisterPass.setText("Đăng kí thành công");
                }
            }
        }


    // Hàm kiểm tra định dạng email bằng regex
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public boolean emailExists(String email) throws SQLException {
        int count = 0;
        try (Connection connection = ConnectSQL.getConnect()) {
            String query = "SELECT COUNT(1) FROM user WHERE email = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            try (ResultSet queryname = preparedStatement.executeQuery()) {
                if (queryname.next()) {
                    count = queryname.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count > 0;
    }
    private boolean userExists(String user) throws SQLException {
        int count1 = 0;
        try (Connection connection = ConnectSQL.getConnect()) {
            String query = "SELECT COUNT(1) FROM user WHERE name = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);

            try (ResultSet queryname1 = preparedStatement.executeQuery()) {
                if (queryname1.next()) {
                    count1 = queryname1.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count1 > 0;
    }

    @FXML
    public void registerLabel(MouseEvent mouseEvent) {
        Login.stageLogin.setScene(Login.sceneRegister);
    }
    public void signinLabel(MouseEvent mouseEvent) {Login.stageLogin.setScene(Login.sceneSignin);}
    public void closeevent(ActionEvent closeEvent) {
        System.exit(0);
    }

    public void verify() {
        try (Connection connection = ConnectSQL.getConnect()) {
            String verifyUser = "SELECT count(1), name FROM user WHERE email = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(verifyUser)) {
                preparedStatement.setString(1, EmailTextSignin.getText());
                preparedStatement.setString(2, hashPassword(PasswordTextSignin.getText()));

                try (ResultSet queryResult = preparedStatement.executeQuery()) {
                    if (queryResult.next()) {
                        int count = queryResult.getInt(1);
                        if (count == 1) {
                            homePage.start(new Stage());
                            Login.stageLogin.close();
                            nameow = queryResult.getString("name");

                        } else {
                            passwordsigningerror.setText("Tài khoản hoặc mật khẩu không tồn tại");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
