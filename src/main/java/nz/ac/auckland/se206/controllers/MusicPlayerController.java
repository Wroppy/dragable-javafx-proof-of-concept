package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import nz.ac.auckland.se206.App;

public class MusicPlayerController {
  MediaPlayer player;

  @FXML
  private void handlePlayFirst() {
    this.playSong("risk-136788");
  }

  @FXML
  private void handlePlaySecond() {
    this.playSong("looking-forward-131923");
  }

  @FXML
  private void handleToCounter(ActionEvent e) {
    Scene scene = ((Button) e.getSource()).getScene();
    try {
      scene.setRoot(App.loadFXML("counter"));
      this.stopMusic();

    } catch (IOException exception) {
      exception.printStackTrace();
    }

  }

  private void playSong(String name) {
    try {
      Media sound = new Media(App.class.getResource("/sounds/" + name + ".mp3").toURI().toString());

      this.stopMusic();
      player = new MediaPlayer(sound);
      player.play();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  private void stopMusic() {
    if (this.player == null) {
      return;
    }

    this.player.stop();
  }

}
