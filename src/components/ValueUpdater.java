package components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ValueUpdater extends Thread {

  ApiHandler api = new ApiHandler();

  /** [btcBIT,ethBIT,btcCOIN,ethCOIN] */
  String[] cryptoValues = {"0", "0", "0", "0"};

  List<UpdateListener> listeners = new ArrayList<>();

  private int interval;

  public ValueUpdater(int upd) {
    interval = upd;
  }

  @Override
  public void run() {
    while (!this.isInterrupted()) {
      try {
        updateNow();
        sleep(interval * 1000);
      } catch (InterruptedException e) {
        System.out.println("New Interval Value: " + interval);
      }
    }
  }

  public void updateNow() {
    try {
      cryptoValues[0] = api.cryptoValue(ApiHandler.BITPANDA, "BTC");
      cryptoValues[1] = api.cryptoValue(ApiHandler.BITPANDA, "ETH");
      cryptoValues[2] = api.cryptoValue(ApiHandler.COINSTATS, 0);
      cryptoValues[3] = api.cryptoValue(ApiHandler.COINSTATS, 1);
      notifyListeners();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void addListener(UpdateListener listener) {
    listeners.add(listener);
  }

  private void notifyListeners() {
    for (UpdateListener listener : listeners) {
      listener.updateValues(cryptoValues[0], cryptoValues[1], cryptoValues[2], cryptoValues[3]);
    }
  }

  public void setInterval(int upd) {
    interval = upd;
  }

  public int getInterval() {
    return interval;
  }
}
