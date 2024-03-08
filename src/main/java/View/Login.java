package View;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import Controller.ControllerLogin;

public class Login extends Application {
    public static Scene sceneSignin;
    public static Scene sceneRegister;
    public static Stage stageLogin;

    @Override
    public void start(Stage stageLogin) throws IOException {
        this.stageLogin = stageLogin;
//        stage1.setFullScreen(true);

        //tạo đối tượng loader để load và xử lý tệp FXML có tên là "signin.fxml".
        FXMLLoader loadersignin = new FXMLLoader(getClass().getResource("signin.fxml"));
        FXMLLoader loaderregister = new FXMLLoader(getClass().getResource("register.fxml"));

        //đọc file fxml và gán nó cho một đối tượng tương ứng.
        Parent fxmlsignin = loadersignin.load();
        Parent fxmlregister = loaderregister.load();

        //được sử dụng để lấy đối tượng controller từ FXMLLoader. Khi bạn load một tệp FXML bằng FXMLLoader, nếu trong FXML có xác định một controller (thông qua thuộc tính fx:controller), FXMLLoader sẽ tạo một instance của controller và bạn có thể lấy nó bằng cách sử dụng phương thức getController().
        ControllerLogin controller = loadersignin.getController();

        sceneSignin = new Scene(fxmlsignin, 339, 600);
        sceneRegister = new Scene(fxmlregister,339, 600);

        sceneSignin.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
        sceneRegister.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());

        controller.setLogin(this);
        stageLogin.setTitle("Account");
        stageLogin.setScene(sceneSignin);
        stageLogin.centerOnScreen();
        stageLogin.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
