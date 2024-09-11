package nz.ac.auckland.se206.components;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.draggable.Draggable;
import java.util.Collections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class ShredderHintComponent extends Pane {
  @FXML
  private Label shredderLabel;

  private List<PaperView> paperViews;
  private List<Rectangle> rects;

  @FXML
  private Rectangle rect1;
  @FXML
  private Rectangle rect2;
  @FXML
  private Rectangle rect3;

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


    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  @FXML
  public void initialize() {
    this.rects = new ArrayList<>();
    this.rects.add(rect1);
    this.rects.add(rect2);
    this.rects.add(rect3);

    this.createPapers();

  }

  public void toggleVisibility() {
    this.setVisible(!this.isVisible());
  }

  @FXML
  private void handleOpenShredderClick() {
    this.toggleVisibility();
  }

  private void createPapers() {
    List<Integer> posssiblePos = new ArrayList<>();
    posssiblePos.add(0);
    posssiblePos.add(1);
    posssiblePos.add(2);

    // Shuffle the possible positions
    Collections.shuffle(posssiblePos);

    for (int i = 0; i < 3; i++) {
      PaperView paperView;
      paperView = new PaperView(this, "paper_" + (i + 1), i);

      this.getChildren().add(paperView);

      System.out.println(paperView.getFitHeight() + " " + paperView.getFitWidth());
      this.paperViews.add(paperView);

      // Move the paper to the correct position
      System.out.println(rects.get(1));
      this.movePaper(paperView, rects.get(posssiblePos.get(i)));

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

  public double getCenter(Rectangle node) {
    return node.getLayoutX() + node.getWidth() / 2;
  }

  public double getCenter(PaperView node) {
    return node.getLayoutX() + node.getFitWidth() / 2;
  }

  public void changeRectangleColour(Rectangle rect, boolean blue) {
    // Set the colour of the rectangle
    if (blue) {
      rect.setFill(javafx.scene.paint.Color.BLUE);
    } else {
      rect.setFill(javafx.scene.paint.Color.RED);
    }

  }

  public void movePaper(PaperView paper, Rectangle rect) {
    // Move the paper on to the rectangle
    paper.setLayoutX(rect.getLayoutX() + rect.getWidth() / 2 - paper.getFitWidth() / 2);
    paper.setLayoutY(rect.getLayoutY() + rect.getHeight() / 2 - paper.getFitHeight() / 2);

  }
}
