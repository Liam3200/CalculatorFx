module com.liam.calculatorapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.liam.calculatorapp to javafx.fxml;
    exports com.liam.calculatorapp;
}