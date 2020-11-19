import javafx.animation.AnimationTimer;
import javafx.concurrent.Service;

import java.util.ArrayList;

public class Controller {
    private Window window;
    private Grid grid;
    private ArrayList<Enemy> enemies;
    private ArrayList<Player> players;
    public Controller(Window window){
        this.window = window;
        grid = new Grid(this.window, 10,10);
        enemies = grid.enemies;
        players = grid.players;

        this.window.start.setOnMouseClicked(e -> {
            if (players.size() > 0){
                this.window.start.setVisible(false);
                start();
            }
            else {
                System.out.println("Add players");
            }
        });
    }
    private void start(){
        // Gameloop usando AnimationTimer.
        for (Player p: players){
            p.executeAction();
        }
//        AnimationTimer animationTimer = new AnimationTimer() {
//            public void handle(long now) {
//                System.out.println("Handle");
//                window.turn++;
//                for (Player p: players){
//                    p.executeAction();
//                }
//
//                for (Enemy e : enemies) {
//                    boolean b = e.executeAction();
//                    if (b){
//                        this.stop();
//                    }
//                }
//            }
//        };
//        animationTimer.start();
    }
}
