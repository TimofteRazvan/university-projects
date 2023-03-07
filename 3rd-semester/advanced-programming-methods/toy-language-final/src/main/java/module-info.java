module com.example.assignment_7l {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assignment_7l to javafx.fxml;
    exports com.example.assignment_7l;
}