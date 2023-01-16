module ru.ucoz.karte.cipherfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;


    opens ru.ucoz.karte.cipherfx to javafx.fxml;
    exports ru.ucoz.karte.cipherfx;
}