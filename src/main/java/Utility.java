import javafx.scene.image.ImageView;

public class Utility {
    public static ImageView getImage(String path){
        return new ImageView(Utility.class.getResource(path).toString());
    }
}
