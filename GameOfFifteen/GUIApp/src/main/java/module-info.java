module it.GTFV.GameOfFifteen.GUIApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires it.GTFV.GameOfFifteen;

    opens it.GTFV.GameOfFifteen.GUIApp to javafx.fxml;
    exports it.GTFV.GameOfFifteen.GUIApp;
}