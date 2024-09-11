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
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class ShredderHintComponent extends Pane {
  @FXML
  private Label shredderLabel;

  private List<PaperView> paperViews;

  public ShredderHintComponent() {
    this.paperViews = new ArrayList<>();
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
      PaperView paperView;
      paperView = new PaperView(this, "paper_" + i, i);

      this.getChildren().add(paperView);
      this.paperViews.add(paperView);
      
    }
  }
}
