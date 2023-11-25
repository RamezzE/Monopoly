module template {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    
    opens monopoly to javafx.fxml;
    exports monopoly;
}
