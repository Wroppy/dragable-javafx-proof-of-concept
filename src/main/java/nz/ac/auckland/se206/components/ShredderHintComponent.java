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

  private void sortPapersByXPosition() {
    this.paperViews.sort(
        (a, b) -> a.isXPositionAfter(b));

    for (int i = 0; i < paperViews.size(); i++) {
      System.out.println(paperViews.get(i).getOrder());
    }
  }

  private boolean arePapersInOrder() {
    for (int i = 0; i < paperViews.size() - 1; i++) {
      if (paperViews.get(i).getOrder() > paperViews.get(i + 1).getOrder()) {
        return false;
      }
    }

    return true;
  }

  private boolean arePapersAcceptableHeight() {
    for (int i = 0; i < paperViews.size() - 1; i++) {
      if (!(paperViews.get(i).isHeightDifferenceAcceptable(paperViews.get(i + 1)))) {
        return false;
      }
    }

    return true;
  }

  @FXML
  public Void compareButtonClicked() {
    sortPapersByXPosition();

    System.out.println("In order " + this.arePapersInOrder());
    System.out.println("Acceptable height " + this.arePapersAcceptableHeight());
    return null;
  }
}
