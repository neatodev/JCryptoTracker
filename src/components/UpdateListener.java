package components;

public interface UpdateListener {

  /**
   * Simple Listener to pass coin values to JavaFX UI.
   *
   * @param btcBit BTC value on Bitpanda
   * @param ethBit ETH value on Bitpanda
   * @param btcCoin BTC value on Coinstats
   * @param ethCoin ETH value on Coinstats
   */
  void updateValues(String btcBit, String ethBit, String btcCoin, String ethCoin);
}
