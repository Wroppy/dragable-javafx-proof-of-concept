package nz.ac.auckland.se206.components;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.draggable.Draggable;

public class PaperView extends ImageView {
  private int order;

  public PaperView(Node parent, String paperFilename, int order) {
    super();
    Image paper = new Image(App.class.getResource("/images/" + paperFilename + ".jpg").toExternalForm());

    this.setImage(paper);

    double width = 40;
    double height = width * 100 / 12; // Aspect ratio

    this.setFitWidth(width);
    this.setFitHeight(height);

    // Sets them to random positions within the parent
    this.setLayoutX(Math.random() * (400 - width));
    this.setLayoutY(Math.random() * (400 - height));

    // Set image to draggable
    new Draggable(this, parent);

    this.order = order;
  }

}
