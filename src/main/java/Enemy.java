import javafx.scene.image.ImageView;

import java.util.Arrays;

public class Enemy extends Entity {
    int health = 100;
    int protection = 0;
    int attackRange = 1;
    int damage = 50;
    int steps = 1;
    int x;
    int y;
    Section section;
    Window window;
    Grid grid;
    protected ImageView image;


    public Enemy(Window window) {
        this.window = window;
        this.grid = window.grid;
    }

    private Section hearSound() {
        int x = section.pos[0];
        int y = section.pos[1];
        for (int i = x - attackRange; i <= x + attackRange; i++) {
            for (int j = y - attackRange; j <= y + attackRange; j++) {
                if (i >= 0 && i <= 9 && j >= 0 && j <= 9) {
                    Section temp = window.grid.getSection(new int[]{i, j});
                    if (temp.sound){
                        return temp;
                    }
                }

            }

        }
        return null;
    }


    private boolean canAttack() {
        int x = section.pos[0];
        int y = section.pos[1];
        for (int i = x - attackRange; i <= x + attackRange; i++) {
            for (int j = y - attackRange; j <= y + attackRange; j++) {
                if (i >= 0 && i <= 9 && j >= 0 && j <= 9) {
                    Section temp = window.grid.getSection(new int[]{i, j});
                    if (temp.entity != null) {
                        System.out.println(temp.entity.getClass().toString());
                        if (temp.entity instanceof Player) {
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }

    private void attack() {
        int x = section.pos[0];
        int y = section.pos[1];
        for (int i = x - attackRange; i <= x + attackRange; i++) {
            for (int j = y - attackRange; j <= y + attackRange; j++) {
                if (i >= 0 && i <= 9 && j >= 0 && j <= 9) {
                    Section temp = window.grid.getSection(new int[]{i, j});
                    if (temp.entity != null) {
                        System.out.println(temp.entity.getClass().toString());
                        if (temp.entity instanceof Player) {
                            System.out.println("Attacked Player");
                            ((Enemy) temp.entity).receiveDamage(damage);
                        }
                    }
                    temp.sound = true;
                }

            }
        }
    }

    public boolean executeAction() {
        Section temp = hearSound();
        if (canAttack()) {
            attack();
        }
        else if (hearSound()!=null) {
            assert temp != null;
            return move(temp);
        }else {
            return move();
        }
        return true;
    }

//    private Player findPlayer(){
//
//    }

    private void die() {
        section.entity = null;
        this.section = null;
        window.gamePane.getChildren().remove(image);
        window.grid.enemies.remove(this);
        // Add item to section
    }

    public void receiveDamage(int damage) {
        health = health - (damage - protection);
        if (health <= 0) {
            die();
        }
    }

    public boolean move(Section section) {
        if (section.pos[0] == 0) {
            return false;
        }
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
        return true;
    }

    public boolean move() {
        if (section.pos[0] - 1 <= 0) {
            return false;
        }
        for (int i = 0; i < steps; i++) {
            int[] buff = new int[]{section.pos[0] - 1, section.pos[1]};
            Section temp = grid.getSection(buff);
            if (temp != null) {
                return move(temp);
            }
        }
        return true;
    }


}

class Ghost extends Enemy {
    public Ghost(Window window) {
        super(window);
        image = Utility.getImage("ghost.gif");
        image.setFitWidth(70);
        image.setFitHeight(70);
        image.setPreserveRatio(true);
        this.window.gamePane.getChildren().addAll(image);
    }
}

class FishTank extends Enemy {
    public FishTank(Window window) {
        super(window);
        image = Utility.getImage("fishtank.gif");
        image.setFitWidth(70);
        image.setFitHeight(70);
        image.setPreserveRatio(true);
        this.window.gamePane.getChildren().addAll(image);
    }
}

class LavaDude extends Enemy {
    public LavaDude(Window window) {
        super(window);
        image = Utility.getImage("lavadude.gif");
        image.setFitWidth(70);
        image.setFitHeight(70);
        image.setPreserveRatio(true);
        this.window.gamePane.getChildren().addAll(image);
    }
}