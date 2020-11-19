import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player extends Entity {
    protected double x;
    protected double y;
    protected int health = 100;
    protected int attackRange = 1;
    protected int experience = 0;
    int damage = 50;
    protected int steps = 1;
    public ArrayList<String> abilities = new ArrayList<>();
    public int actions = 3;
    protected ImageView image;
    protected Section section = null;
    protected Window window;


    private void getAttackEnemies() {

    }

    public void attack() {
        int x = section.pos[0];
        int y = section.pos[1];
        for (int i = x - attackRange; i <= x + attackRange; i++) {
            for (int j = y - attackRange; j <= y + attackRange; j++) {
                if (i >= 0 && i <= 9 && j >= 0 && j <= 9) {
                    Section temp = window.grid.getSection(new int[]{i, j});
                    if (temp.entity != null) {
                        System.out.println(temp.entity.getClass().toString());
                        if (temp.entity instanceof Enemy) {
                            System.out.println("Attacked enemy");
                            ((Enemy) temp.entity).receiveDamage(damage);
                        }
                    }
                    temp.sound = true;
                }

            }
        }
    }

    public Player(Window window) {
        this.window = window;
    }

    public void changeX(double x) {
        this.x = x;
        if (image != null) {
            image.setTranslateX(this.x);
        }
    }

    public void changeY(double y) {
        this.y = y;
        if (image != null) {
            image.setTranslateY(this.y);
        }
    }

    public void move(Section section) {
        if (section.entity == null) {
            if (this.section != null) {
                this.section.entity = null;
            }
            this.section = section;
            this.section.entity = this;
            this.x = section.x;
            this.y = section.y;
            Utility.transition(image, x, y);
        } else {
            System.out.println("Already an Entity in " + Arrays.toString(section.pos));
        }
    }

    public void move(Scene scene, Stage stage) {
        System.out.println("Move");
        AnimationTimer animationTimer = new AnimationTimer() {
            int temp = 0;
            boolean set = false;

            @Override
            public void handle(long l) {
                if (!set) {
                    System.out.println("Set");
                    scene.setOnKeyPressed(e -> {
                        System.out.println("KeyPressed");
                        int[] buff;
                        Grid grid = section.grid;
                        if (e.getCode() == KeyCode.ENTER) {
                            scene.setOnKeyPressed(null);
                            this.stop();
                        }
                        if (e.getCode() == KeyCode.W) {
                            if (section.pos[1] != 0) {
                                System.out.println("W");
                                buff = new int[]{section.pos[0], section.pos[1] - 1};
                                move(grid.getSection(buff));
                                temp++;
                            }
                        }
                        if (e.getCode() == KeyCode.S) {
                            if (section.pos[1] != 9) {
                                System.out.println("S");
                                buff = new int[]{section.pos[0], section.pos[1] + 1};
                                move(grid.getSection(buff));
                                temp++;
                            }
                        }
                        if (e.getCode() == KeyCode.A) {
                            if (section.pos[0] != 0) {
                                System.out.println("A");
                                buff = new int[]{section.pos[0] - 1, section.pos[1]};
                                move(grid.getSection(buff));
                                temp++;
                            }
                        }
                        if (e.getCode() == KeyCode.D) {
                            if (section.pos[0] != 9) {
                                System.out.println("D");
                                buff = new int[]{section.pos[0] + 1, section.pos[1]};
                                move(grid.getSection(buff));
                                temp++;
                            }
                        }
                    });
                    set = true;
                }
                if (temp >= steps) {
                    scene.setOnKeyPressed(null);
                    this.stop();
                }
            }
        };
        animationTimer.start();
    }


    public void executeAction() {

        Window.playerChoice(this);

    }

    protected void configureImage() {
        image.setFitHeight(70);
        image.setFitWidth(70);
        image.setPreserveRatio(true);
        image.setOnMouseDragged(e -> {
            if (section == null) {
                changeX(e.getSceneX() - 35);
                changeY(e.getSceneY() - 35);
                window.cursor.setPlayer(this);
            }
            image.setMouseTransparent(true);
            e.consume();
        });
    }
}


class Snowman extends Player {
    public Snowman(Window window) {
        super(window);
        image = Utility.getImage("snowman.gif");
        configureImage();
        window.gamePane.getChildren().addAll(image);
    }
}

class Robot extends Player {
    public Robot(Window window) {
        super(window);
        image = Utility.getImage("robot.gif");
        configureImage();
        window.gamePane.getChildren().addAll(image);
    }
}

class Angel extends Player {
    public Angel(Window window) {
        super(window);
        image = Utility.getImage("angel.gif");
        configureImage();
        window.gamePane.getChildren().addAll(image);
    }
}
