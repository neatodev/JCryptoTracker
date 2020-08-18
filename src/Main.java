import components.ApiHandler;
import javafx.application.Application;
import javafx.controller.CryptoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Application to track & update Bitpanda/Coinstats BTC+ETH values.
 *
 * @author Mario Schweidler
 */
public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    AnchorPane window = FXMLLoader.load(getClass().getResource("javafx/resources/window.fxml"));
    stage.setScene(new Scene(window));
    stage.setTitle("JCryptoTracker");
    stage.getIcons().add(new Image("resources/weird_icon.png"));
    stage.setResizable(false);
    stage.show();
  }
}
