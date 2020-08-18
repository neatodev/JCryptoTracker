package javafx.controller;

import components.ValueUpdater;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;
import javafx.controller.view.UpdateView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Text;

public class CryptoController implements Initializable {

  @FXML Text bitBTC;

  @FXML Text bitETH;

  @FXML Text coinBTC;

  @FXML Text coinETH;

  @FXML Label intervalValue;

  @FXML Text lastRefreshValue;

  @FXML TextField updateIntervalField;

  @FXML Button applyButton;

  @FXML Button refreshNowButton;

  @FXML Hyperlink githubLink;

  private ValueUpdater updater;

  public CryptoController() {}

  /**
   * Called to initialize a controller after its root element has been completely processed.
   *
   * @param url The location used to resolve relative paths for the root object, or <tt>null</tt> if
   *     the location is not known.
   * @param resourceBundle The resources used to localize the root object, or <tt>null</tt> if
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    updater = new ValueUpdater(30);
    configureTextField(updateIntervalField);
    intervalValue.setText("30");
    UpdateView view = new UpdateView(bitBTC, bitETH, coinBTC, coinETH, lastRefreshValue);
    updater.addListener(view);
    updater.start();
  }

  /** Updates the coin values and resets the sleep interval. */
  @FXML
  private void onClickRefresh() {
    updater.updateNow();
    updater.interrupt();
  }

  /**
   * If the corresponding textfield isn't empty, apply its contents as new value for the interval.
   */
  @FXML
  private void onClickApply() {
    if (!updateIntervalField.getText().trim().isEmpty()) {
      updater.interrupt();
      updater.setInterval(Integer.parseInt(updateIntervalField.getText().trim()));
      intervalValue.setText(updateIntervalField.getText().trim());
      updateIntervalField.setText("");
    }
  }

  /**
   * Opens Github Repo URL in browser.
   *
   * @throws IOException If there is something wrong with the URL
   * @throws URISyntaxException If there is something wrong with the URI
   */
  @FXML
  private void onClickHyperlink() throws IOException, URISyntaxException {
    Desktop.getDesktop().browse(new URL("https://github.com/neatodev/JCryptoTracker").toURI());
  }

  /**
   * Configures the text field to only accept numerical values.
   *
   * @param field The text field
   */
  private void configureTextField(TextField field) {
    DecimalFormat format = new DecimalFormat("#");

    final TextFormatter<Object> decimalTextFormatter =
        new TextFormatter<>(
            change -> {
              if (change.getControlNewText().isEmpty()) {
                return change;
              }
              ParsePosition parsePosition = new ParsePosition(0);
              Object object = format.parse(change.getControlNewText(), parsePosition);

              if (object == null
                  || parsePosition.getIndex() < change.getControlNewText().length()) {
                return null;
              } else {
                return change;
              }
            });
    field.setTextFormatter(decimalTextFormatter);
  }
}
