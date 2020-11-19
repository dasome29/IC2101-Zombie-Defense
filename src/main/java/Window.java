import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.concurrent.atomic.AtomicInteger;

public class Window {
    public Stage stage;
    public Pane root;
    public Scene scene;
    public StackPane gamePane;
    public StackPane infoPane;
    private ScrollPane scrollPane;
    private StackPane playersPane;
    public Grid grid;
    public Cursor cursor;
    public int turn = 0;
    public ImageView start = Utility.getImage("playButton.png");


    public Window(Stage stage) {
        cursor = new Cursor();
        this.stage = stage;


        // Root as main Pane
        root = new Pane();

        //Set Game Pane
        gamePane = new StackPane();
        gamePane.setPrefSize(800, 800);
        gamePane.setTranslateX(0);
        gamePane.setTranslateY(0);
        gamePane.setAlignment(Pos.TOP_LEFT);

        infoPane = new StackPane();
        infoPane.setPrefSize(400, 400);
        infoPane.setTranslateX(800);
        infoPane.setTranslateY(0);
        infoPane.setAlignment(Pos.TOP_CENTER);

        playersPane = new StackPane();
        playersPane.setPrefSize(400, 400);
        playersPane.setTranslateX(800);
        playersPane.setTranslateY(400);
        playersPane.setAlignment(Pos.TOP_CENTER);

        setPlayerButtons();

        start.setTranslateY(100);

        start.setFitHeight(100);
        start.setFitWidth(100);

        start.setPreserveRatio(true);


        infoPane.getChildren().addAll(start);


        root.getChildren().addAll(gamePane, infoPane, playersPane);

        // Set color gradient to scene
        scene = new Scene(root, 1200, 800);



        // Stage configuration
        root.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("background.png").toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.setTitle("Zombie Defense");
        this.stage.show();
    }

    public void setGrid(Grid grid){
        this.grid = grid;
    }
    private void setPlayerButtons() {
        ImageView angel = Utility.getImage("angel.gif");
        angel.setTranslateY(50);
        angel.setOnMouseClicked(e -> {
            Player player = new Angel(this);
            cursor.setPlayer(player);
        });
        ImageView robot = Utility.getImage("robot.gif");
        robot.setOnMouseClicked(e -> {
            Player player = new Robot(this);
            cursor.setPlayer(player);
        });
        robot.setTranslateY(150);
        ImageView snowman = Utility.getImage("snowman.gif");
        snowman.setOnMouseClicked(e -> {
            Player player = new Snowman(this);
            cursor.setPlayer(player);
        });
        snowman.setTranslateY(250);


        playersPane.getChildren().addAll(angel, robot, snowman);
    }

    public static void playerChoice(Player player){
        AtomicInteger turns = new AtomicInteger(0);
        Label label = new Label("Label");
        Stage stage = new Stage();
        stage.setResizable(false);
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(pane, 200, 400);
        ImageView attackButton = Utility.getImage("sword.png");
        attackButton.setFitWidth(100);
        attackButton.setFitHeight(100);
        attackButton.setPreserveRatio(true);
        ImageView moveButton = Utility.getImage("move.png");
        moveButton.setFitWidth(100);
        moveButton.setFitHeight(100);
        moveButton.setPreserveRatio(true);
        ImageView useItemButton = Utility.getImage("vault.png");
        useItemButton.setFitWidth(100);
        useItemButton.setFitHeight(100);
        useItemButton.setPreserveRatio(true);
        attackButton.setTranslateY(50);
        moveButton.setTranslateY(150);
        useItemButton.setTranslateY(250);
        attackButton.setOnMouseClicked(e -> {
            player.attack();
            turns.getAndIncrement();
            if (turns.get() == player.actions){
                stage.close();
            }
        });
        moveButton.setOnMouseClicked(e -> {
            player.move(scene, stage);
            turns.getAndIncrement();
            if (turns.get() == player.actions){
                stage.close();
            }
        });
        pane.getChildren().addAll(label, attackButton, moveButton, useItemButton);
        scene.setOnKeyPressed(e ->{
            if (e.getCode()== KeyCode.ENTER){
                stage.close();
            }
        });
        stage.setScene(scene);
        stage.initOwner(player.window.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();

    }
}

class Cursor {
    private Player player = null;

    public Player getPlayer() {
        Player temp = player;
        player = null;
        return temp;
    }

    public void setPlayer(Player player) {
        if (this.player != null){
            this.player.image.setDisable(true);
            this.player.image.setVisible(false);
        }
        this.player = player;
    }
}


