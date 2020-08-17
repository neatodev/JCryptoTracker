package components;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ApiHandler {

  /** Custom Coinstats API URL that contains BTC + ETH details */
  public static final String COINSTATS =
      "https://api.coinstats.app/public/v1/coins?skip=0&limit=2&currency=EUR";

  /** Bitpanda API that contains all cryptocurrencies available on the service */
  public static final String BITPANDA = "https://api.bitpanda.com/v1/ticker";

  /**
   * Connects to Bitpanda/Coinstats API link.
   *
   * @param link The URL als a string
   * @throws IOException if the URL is invalid
   * @return a URLConnection object
   */
  private URLConnection connectToApi(String link) throws IOException {
    URLConnection req;
    try {
      URL url = new URL(link);
      req = url.openConnection();
      req.connect();
    } catch (IOException e) {
      throw new IOException("Could not connect to URL '" + link + "'\n" + e);
    }
    return req;
  }

  /**
   * Assigns the value of the desired cryptocurrency to a String.
   *
   * @param sUrl API URL
   * @param currency The desired currency ("BTC","ETH")
   * @throws IOException if the URL is invalid
   * @return The cryptocurrency value
   */
  public String cryptoValue(String sUrl, String currency) throws IOException {
    URLConnection request = connectToApi(sUrl);
    JsonParser parser = new JsonParser();
    JsonElement root = parser.parse(new InputStreamReader((InputStream) request.getContent()));
    JsonObject rootobj = root.getAsJsonObject();
    JsonObject obj = (JsonObject) rootobj.get(currency);
    return obj.get("EUR").toString().replaceAll("^\"|\"$", "");
  }

  /**
   * Assigns the value of the desired cryptocurrency to a String.
   *
   * @param sUrl API URL
   * @param arrayValue value of the (Coinstats) JSON Array (0=BTC,1=ETH)
   * @throws IOException if the URL is invalid
   * @return The cryptocurrency value
   */
  public String cryptoValue(String sUrl, int arrayValue) throws IOException {
    URLConnection request = connectToApi(sUrl);
    JsonParser parser = new JsonParser();
    JsonElement root = parser.parse(new InputStreamReader((InputStream) request.getContent()));
    JsonObject object = root.getAsJsonObject();
    JsonArray coinArray = object.get("coins").getAsJsonArray();
    object = coinArray.get(arrayValue).getAsJsonObject();
    String result = object.get("price").toString();
    return result.substring(0, result.lastIndexOf("."))
        + result.substring(result.indexOf(".")).substring(0, 3);
  }
}
