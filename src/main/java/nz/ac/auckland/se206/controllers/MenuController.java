package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import nz.ac.auckland.se206.components.ShredderHintComponent;

public class MenuController {
  @FXML
  Pane pane;

  ShredderHintComponent shredderPane;

  @FXML
  public void initialize() {

    shredderPane = new ShredderHintComponent();
    pane.getChildren().add(shredderPane);

  }

  public MenuController() {
  }

  @FXML
  private Void handleOpenShredderClick() {
    this.toggleShredderHint();
    return null;
  }

  private void toggleShredderHint() {
    shredderPane.toggleVisibility();
  }
}
