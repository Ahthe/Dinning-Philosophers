module stars.dinningphilosophers {
    requires javafx.controls;
    requires javafx.fxml;


    opens stars.dinningphilosophers to javafx.fxml;
    exports stars.dinningphilosophers;
}