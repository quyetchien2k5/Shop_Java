package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Controller.ControllerHomePage;

import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.io.IOException;

public class HomePage extends Application {
    public static Stage stageHomePage;
    public Scene sceneHome;

    @Override
    public void start(Stage stageHomePage) throws IOException {
        this.stageHomePage = stageHomePage;

        FXMLLoader loaderhomepage = new FXMLLoader(getClass().getResource("homepage.fxml"));
        Parent fxmlhome = loaderhomepage.load();

        ControllerHomePage controllerhome = loaderhomepage.getController();

        sceneHome = new Scene(fxmlhome, 1366, 745);
        sceneHome.getStylesheets().add(getClass().getResource("homepage.css").toExternalForm());

        controllerhome.setHomePage(this);
        stageHomePage.centerOnScreen();
        stageHomePage.setTitle("Home");
        stageHomePage.setScene(sceneHome);
        stageHomePage.show();
    }
}
