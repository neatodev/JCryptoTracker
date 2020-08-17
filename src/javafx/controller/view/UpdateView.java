package javafx.controller.view;

import components.UpdateListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.text.Text;

public class UpdateView implements UpdateListener {

  Text bitBTC;
  Text bitETH;
  Text coinBTC;
  Text coinETH;
  Text lastRefresh;

  public UpdateView(Text btc1, Text eth1, Text btc2, Text eth2, Text refresh) {
    bitBTC = btc1;
    bitETH = eth1;
    coinBTC = btc2;
    coinETH = eth2;
    lastRefresh = refresh;
  }

  @Override
  public void updateValues(String btcBit, String ethBit, String btcCoin, String ethCoin) {
    bitBTC.setText(btcBit);
    bitETH.setText(ethBit);
    coinBTC.setText(btcCoin);
    coinETH.setText(ethCoin);
    lastRefresh.setText(new SimpleDateFormat("dd.MM.yy HH:mm:ss").format(new Date()));
  }
}
