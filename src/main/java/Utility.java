import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Utility {
    public static ImageView getImage(String path){
        return new ImageView(Utility.class.getResource(path).toString());
    }
    public static void transition(Node node, double x, double y){
        TranslateTransition t = new TranslateTransition();
        t.setDuration(Duration.seconds(3));
        t.setToX(x);
        t.setToY(y);
        t.setNode(node);
        t.play();
        System.out.println("Transition ran");
    }
}
