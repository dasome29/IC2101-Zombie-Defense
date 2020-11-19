import javafx.scene.image.ImageView;

public class Enemy extends Entity{
    protected ImageView image;
    public boolean executeAction(){
        return true;
    }
}

class Ghost extends Enemy{
    public Ghost(){
        super();
        image = Utility.getImage("");
        image.setFitWidth(70);
        image.setFitHeight(70);
        image.setPreserveRatio(true);
    }
}

class FishTank extends Enemy{
    public FishTank(){
        super();
        image = Utility.getImage("");
        image.setFitWidth(70);
        image.setFitHeight(70);
        image.setPreserveRatio(true);
    }
}

class LavaDude extends Enemy{
    public LavaDude(){
        super();
        image = Utility.getImage("");
        image.setFitWidth(70);
        image.setFitHeight(70);
        image.setPreserveRatio(true);
    }
}