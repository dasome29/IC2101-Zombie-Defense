import javafx.animation.AnimationTimer;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.sql.Struct;
import java.util.ArrayList;

public class Controller {
    private Window window;
    private Grid grid;
    private ArrayList<Enemy> enemies;
    private ArrayList<Player> players;

    public Controller(Window window) {
        this.window = window;
        enemies = this.window.grid.enemies;
        players = this.window.grid.players;

        this.window.start.setOnMouseClicked(e -> {
            if (players.size() > 0) {
                this.window.start.setVisible(false);
                start();
            } else {
                System.out.println("Add players");
            }
        });
    }

    private void start() {
        boolean gameOver = false;
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignored) {
                }
                return null;
            }
        };
        while (!gameOver) {
            // Gameloop usando AnimationTimer.
            for (Player p : players) {
                p.executeAction();
            }
            window.grid.spawn();
            for (Enemy e :
                    enemies) {
                if (!e.executeAction()) {
                    gameOver = true;
                    window.stage.close();
                }
            }
            window.grid.restoreSound();
            new Thread(sleeper).start();
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
