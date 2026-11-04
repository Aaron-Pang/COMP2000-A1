package supplementary;

import java.util.Map;
import java.util.Set;

import growables.Growable;
import java.awt.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;

public class LightningRadiusCollector implements Collector<Growable,
                                                             Map<Boolean, List<Growable>>,
                                                             Map<Boolean, List<Growable>>> {

    Point pos;

    public LightningRadiusCollector(Point pos) {
        this.pos = pos;
    }

    

    public boolean isNear(Growable g) {
        return(g.getDistanceFrom(pos) < 200);
    }

    @Override
    public Supplier<Map<Boolean, List<Growable>>> supplier() {
        //Create map to be used by accumulator and prefill it with either near or far
        return () -> new HashMap<Boolean, List<Growable>>() {{
            put(true, new ArrayList<Growable>());
            put(false, new ArrayList<Growable>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Growable>>, Growable> accumulator() {
        return (Map<Boolean, List<Growable>> acc, Growable candidate) -> {
            acc.get(isNear(candidate))
               .add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Growable>>> combiner() {
        //Allow collector to work in parallel by combining the results of multiple maps
        return (Map<Boolean, List<Growable>> map1,
                Map<Boolean, List<Growable>> map2) -> {
                    map1.get(true).addAll(map2.get(true));
                    map1.get(false).addAll(map2.get(false));
                    return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Growable>>,
                    Map<Boolean, List<Growable>>> finisher() {
        //The hell is this??
        return Function.<Map<Boolean, List<Growable>>>identity();
    }

    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.valueOf("IDENTITY_FINISH")));
    }
}
