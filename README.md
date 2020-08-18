# JCryptoTracker

## What does it do?
It's a very small application that allows you to track current prices for BTC&ETH on both Coinstats and Bitpanda in Euro.

## Requirements
[JDK 14](https://jdk.java.net/14/) & [JFX 14](https://gluonhq.com/products/javafx/)

## Usage
Extract the 7z file and edit the launch.bat and replace "JAVAFX_PATH" with the correct path/environment variable for JavaFX14.

Example:
```
java --module-path "JAVAFX_PATH" --add-modules javafx.controls,javafx.fxml -jar JCryptoTracker.jar
// changed to:
java --module-path C:\OpenJDK\javafx-sdk-14.0.2.1\lib --add-modules javafx.controls,javafx.fxml -jar JCryptoTracker.jar
// alternatively, if you have an environment variable set up, it could be:
java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml -jar JCryptoTracker.jar
```
Now simply double-click the batch file and it should run.

## Preview

![Preview](https://user-images.githubusercontent.com/49599979/90552924-dd738900-e193-11ea-8c7d-3281b9f7d810.png)


## License
This work is licensed under a
[Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License][cc-by].

[![CC BY 4.0][cc-by-shield]][cc-by]

[cc-by]: https://creativecommons.org/licenses/by-nc-sa/4.0/
[cc-by-shield]: https://licensebuttons.net/l/by-nc-sa/4.0/80x15.png
