package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  private Scene scene;

  public static void main(String[] args) {
    launch();
  }

  public static FXMLLoader loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
    return fxmlLoader;
  }

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = loadFXML("menu");
    scene = new Scene(fxmlLoader.load(), 640, 480);
    stage.setScene(scene);
    stage.show();
  }
}
