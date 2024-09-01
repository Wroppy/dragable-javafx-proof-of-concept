package nz.ac.auckland.se206.draggable;

import javafx.scene.Node;

public class Draggable {

  private double mouseAnchorX;
  private double mouseAnchorY;

  public Draggable(Node node, Node parent) {
    node.setOnMousePressed(e -> {
      mouseAnchorX = e.getSceneX() - node.getLayoutX();
      mouseAnchorY = e.getSceneY() - node.getLayoutY();

    });

    node.setOnMouseDragged(e -> {
      // Checks that the node is not dragged out of the parent
      if (e.getSceneX() - mouseAnchorX < 0) {
        return;
      }
      // Checks that the node is not dragged out of the parent

      if (e.getSceneX() - mouseAnchorX > parent.getLayoutBounds().getWidth() - node.getLayoutBounds().getWidth()) {
        return;
      }

      if (e.getSceneY() - mouseAnchorY < 0) {
        return;
      }

      if (e.getSceneY() - mouseAnchorY > parent.getLayoutBounds().getHeight() - node.getLayoutBounds().getHeight()) {
        return;
      }

      node.setLayoutX(e.getSceneX() - mouseAnchorX);
      node.setLayoutY(e.getSceneY() - mouseAnchorY);

    });

  }

}
