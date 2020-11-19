import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    Window window;
    ArrayList<ArrayList<Section>> sections = new ArrayList<>();
    public ArrayList<Player> players = new ArrayList<>();
    public ArrayList<Enemy> enemies = new ArrayList<>();
    int x;
    int y;

    public Grid(Window window, int x, int y) {
        this.window = window;
        this.x = x;
        this.y = y;
        initialize();
    }

    public Section getSection(int[] pos) {
        for (ArrayList<Section> i :
                sections) {
            for (Section s :
                    i) {
                if (Arrays.equals(s.pos, pos)) {
                    return s;
                }
            }
        }
        return null;
    }

    private void initialize() {
        for (int i = 0; i < x; i++) {
            ArrayList<Section> temp = new ArrayList<>();
            for (int j = 0; j < y; j++) {
                temp.add(new Section(50 + (i * 73), 35 + (j * 73), this, new int[]{i, j}));
            }
            sections.add(temp);
        }
    }

}

enum Type {
    ROCK,
    BUSH,
    NORMAL,
    SPAWN
}

class Section {
    Window window;
    ImageView image;
    ImageView imageType;
    Type type = Type.NORMAL;
    int x;
    int y;
    int sound = 0;
    int [] pos;
    int size = 70;
    public Grid grid;
    Entity entity = null;
    Rectangle rectangle = new Rectangle(size, size);

    public Section(int x, int y, Grid grid, int[] pos) {
        this.x = x;
        this.y = y;
        this.pos = pos;
        this.grid = grid;
        image = Utility.getImage("section.png");
        image.setTranslateX(this.x);
        image.setTranslateY(this.y);
        image.setFitWidth(size);
        image.setFitHeight(size);
        rectangle.setTranslateX(this.x);
        rectangle.setTranslateY(this.y);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setOnMouseClicked(e -> {
            System.out.println("Clicked");
            Player player = window.cursor.getPlayer();
            if (entity == null) {
                if (player != null) {
                    grid.players.add(player);
                    player.move(this);
                    window.cursor.setPlayer(null);
                    entity = player;
                }
            }
        });
        this.window = grid.window;
        this.window.gamePane.getChildren().addAll(image, rectangle);
//        System.out.println("New section");
    }

    public Section(int x, int y, Grid grid, int[] pos, Type type) {
        this(x, y, grid, pos);
        setType(type);
    }

    public void setType(Type type) {
        // Do specific things for the type of section.
        this.type = type;
        switch (type) {
            case BUSH:
                imageType = Utility.getImage("bush.png");
                break;
            case ROCK:
                imageType = Utility.getImage("rock.png");
                break;
            case SPAWN:
                imageType = Utility.getImage("spawn.png");
                break;
            default:
                break;
        }
        if (imageType != null) {
            imageType.setTranslateX(x);
            imageType.setTranslateY(y);
            window.gamePane.getChildren().addAll(imageType);
        }
    }

}

