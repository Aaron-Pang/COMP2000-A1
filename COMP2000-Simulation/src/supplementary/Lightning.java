package supplementary;

import growables.Growable;
import java.awt.Point;
import java.util.List;
import java.util.Map;
import placed_objects.Ground;

public class Lightning {

    // During stormy weather, lightning might strike a point.
    // The bolt will request a stream of the growables in the window.
    // It will create a collection of them, grouped by their distance from the
    // impact point.
    // Growables will be affected differently by the lightning depending on how far
    // away they are.



    public static Map<Boolean, List<Growable>> categoriseGrowables(Ground ground, Point pos) {
        Map<Boolean, List<Growable>> growablesByDistance = ground.getGrowables().stream()
                .filter(g -> g.getDistanceFrom(pos) < 300)
                .parallel()
                .collect(new LightningRadiusCollector(pos));
        //System.out.println(growablesByDistance);
        return growablesByDistance;
    }

    public static void strike(Ground ground, Point p) {
        new ImpactPoint(p, ground);     //This object adds itself to the window so we don't need to track it
        Map<Boolean, List<Growable>> affectedObjects = categoriseGrowables(ground, p);
        List<Growable> near = affectedObjects.get(true);
        if(near != null) {
            for(Growable g : near) {
                g.setDead();
            }
        }
    }
}