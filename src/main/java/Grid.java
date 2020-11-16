import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Grid {
    Window window;
    ArrayList<ArrayList<Section>> sections = new ArrayList<>();
    int x;
    int y;

    public Grid(Window window, int x, int y) {
        this.window = window;
        this.x = x;
        this.y = y;
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < x; i++) {
            ArrayList<Section> temp = new ArrayList<>();
            for (int j = 0; j < y; j++) {
                temp.add(new Section(50 + (i * 73), 35 + (j * 73), window));
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
    int size = 70;

    public Section(int x, int y, Window window) {
        this.x = x;
        this.y = y;
        image = Utility.getImage("section.png");
        image.setTranslateX(this.x);
        image.setTranslateY(this.y);
        image.setFitWidth(size);
        image.setFitHeight(size);
        this.window = window;
        this.window.gamePane.getChildren().addAll(image);
//        System.out.println("New section");
    }

    public Section(int x, int y, Window window, Type type) {
        this(x, y, window);
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

