module com.example.projet_java_algo_simon_lucot {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens com.example.projet_java_algo_simon_lucot to javafx.fxml;
    exports com.example.projet_java_algo_simon_lucot;
}