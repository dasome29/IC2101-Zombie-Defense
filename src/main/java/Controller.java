import java.util.ArrayList;

public class Controller {
    private Window window;
    private Grid grid;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    public Controller(Window window){
        this.window = window;
        grid = new Grid(this.window, 10,10);
    }
    private void start(){

    }
}
