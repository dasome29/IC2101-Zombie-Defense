import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        Window window = new Window(primaryStage);
        Grid grid = new Grid(window, 10, 10);
        window.setGrid(grid);
        Controller controller = new Controller(window);
    }
}
