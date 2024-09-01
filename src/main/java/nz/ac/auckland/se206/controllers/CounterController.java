package nz.ac.auckland.se206.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nz.ac.auckland.se206.App;

public class CounterController {
  private int count = 0;

  @FXML
  private Button incrementButton;

  @FXML
  private Button decrementButton;

  @FXML
  private Button resetButton;

  @FXML
  private Button switchButton;

  @FXML
  private Label numberLabel;

  @FXML
  private void onIncrement() {
    count++;
    updateLabel();

  }

  @FXML
  private void onDecrement() {
    if (count == 0) {
      return;
    }
    count--;
    updateLabel();

  }

  @FXML
  private void onSwitch(ActionEvent e) {
    Scene currentScene = ((Button) e.getSource()).getScene();

    try {
      currentScene.setRoot(App.loadFXML("musicplayer").load());
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  @FXML
  private void onReset() {
    count = 0;

    updateLabel();
  }

  private void updateLabel() {
    numberLabel.setText(Integer.toString(count));
  }
}