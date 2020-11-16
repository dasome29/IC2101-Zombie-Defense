import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Window {
    public Stage stage;
    public Pane root;
    public Scene scene;
    public StackPane gamePane;

    public Window(Stage stage){
        this.stage = stage;
        root = new Pane();
        gamePane = new StackPane();
        gamePane.setPrefSize(1000, 800);
        gamePane.setTranslateX(0);
        gamePane.setTranslateY(0);
        gamePane.setAlignment(Pos.TOP_LEFT);
        root.getChildren().addAll(gamePane);
        scene = new Scene(root, 1200, 800);
        Stop[] stops = new Stop[] { new Stop(0, Color.LIGHTGRAY), new Stop(1, Color.BLACK)};
        LinearGradient linear = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        scene.setFill(linear);

//        ImageView imageView = Utility.getImage("section.png");
//        imageView.setFitWidth(50);
//        imageView.setFitHeight(50);
//        imageView.setTranslateX(0);
//        imageView.setTranslateY(0);
//        gamePane.getChildren().addAll(imageView);


        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.setTitle("Zombie Defense");
        this.stage.show();
    }
}
