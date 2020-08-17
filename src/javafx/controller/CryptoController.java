package javafx;

import components.ApiHandler;
import components.ValueUpdater;

public class CryptoController {

  ValueUpdater updater;


  public CryptoController() {
    updater = new ValueUpdater(60);
  }
}
