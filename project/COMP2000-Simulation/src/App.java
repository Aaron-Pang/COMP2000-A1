import java.awt.*;

public class App {
    public static void main(String[] args) {
        Window window = new Window();

        int delay = 50; //Refresh 20 times per second

        //TODO: needs refresh rate code here or in controller - Allie

        new Sunflower(new Point(200, 200), window.ground);

        window.addToGround(new Patch(50, new Point(75, 50)), null);
    }
}
