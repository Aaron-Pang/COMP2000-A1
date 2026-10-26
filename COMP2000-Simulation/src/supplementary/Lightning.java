package supplementary;

import growables.Growable;
import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import placed_objects.Ground;

public class Lightning {

    // During stormy weather, lightning might strike a point.
    // The bolt will request a stream of the growables in the window.
    // It will create a collection of them, grouped by their distance from the
    // impact point.
    // Growables will be affected differently by the lightning depending on how far
    // away they are.



    public static Map<String, List<Growable>> categoriseGrowables(Ground ground, Point pos) {
        Map<String, List<Growable>> growablesByDistance = ground.getGrowables().stream()
                .filter(g -> g.getDistanceFrom(pos) < 300)
                .parallel()
                .collect(Collectors.groupingBy(g -> {
                    if (g.getDistanceFrom(pos) < 200)
                        return "NEAR";
                    else
                        return "FAR";
                }));
        System.out.println(growablesByDistance);
        return growablesByDistance;
    }
}