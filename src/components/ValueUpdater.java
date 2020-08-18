package components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ValueUpdater extends Thread {

  private ApiHandler api = new ApiHandler();

  /** [btcBIT,ethBIT,btcCOIN,ethCOIN] */
  private String[] cryptoValues = {"0", "0", "0", "0"};

  private List<UpdateListener> listeners = new ArrayList<>();

  private int interval;

  /**
   * Constructor
   *
   * @param upd interval value
   */
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

  /**
   * Updates coin values and stores them in a String array, then notifies listener(s) with the new
   * values.
   */
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

  /**
   * Adds a listener to the UpdateListener list.
   *
   * @param listener The listener to add
   */
  public void addListener(UpdateListener listener) {
    listeners.add(listener);
  }

  /** Notifies listeners with changes. */
  private void notifyListeners() {
    for (UpdateListener listener : listeners) {
      listener.updateValues(cryptoValues[0], cryptoValues[1], cryptoValues[2], cryptoValues[3]);
    }
  }

  /**
   * Setter for the sleep interval.
   *
   * @param upd new sleep interval
   */
  public void setInterval(int upd) {
    interval = upd;
  }

  /**
   * Getter for the sleep interval.
   *
   * @return the current sleep interval
   */
  public int getInterval() {
    return interval;
  }
}
