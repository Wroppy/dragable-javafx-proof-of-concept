package nz.ac.auckland.se206.components;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.draggable.Draggable;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class ShredderHintComponent extends Pane {
  @FXML
  private Label shredderLabel;

  Draggable paper;

  public ShredderHintComponent() {
    this.setId("ShredderHintComponent");

    try {
      FXMLLoader fxmlLoader = App.loadFXML("shredder");

      fxmlLoader.setController(this);
      fxmlLoader.setRoot(this);

      Parent root = fxmlLoader.load();

      root.resize(400, 400);

      this.setVisible(false);

      // Centers
      this.setLayoutX(125);
      this.setLayoutY(50);

      this.createPapers();

    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  public void toggleVisibility() {
    this.setVisible(!this.isVisible());
  }

  @FXML
  private void handleOpenShredderClick() {
    this.toggleVisibility();
  }

  private void createPapers() {
    for (int i = 1; i <= 6; i++) {
      Image paper = new Image(App.class.getResource("/images/paper_" + i + ".jpg").toExternalForm());
      ImageView paperView = new ImageView(paper);
      double width = 40;
      double height = width * 100 / 12; // Aspect ratio of the paper
      paperView.setFitWidth(width);
      paperView.setFitHeight(height);

      // Sets them to random positions within the parent  
      paperView.setLayoutX(Math.random() * (400 - width));
      paperView.setLayoutY(Math.random() * (400 - height));



      // Set the draggable
      new Draggable(paperView, this);

      this.getChildren().add(paperView);

    }
  }

}
