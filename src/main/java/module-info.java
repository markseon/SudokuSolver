module com.seonm.sudokusolver {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.seonm.sudokusolver to javafx.fxml;
    exports com.seonm.sudokusolver;
}
