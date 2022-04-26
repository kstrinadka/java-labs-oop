module com.strinadkakirill.javalabs.lab3_minesweeper.lab3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.strinadkakirill.javalabs.lab3_minesweeper.lab3 to javafx.fxml;
    exports com.strinadkakirill.javalabs.lab3_minesweeper.lab3;
}