package growables;

import java.util.ArrayList;

public interface GrowableObserver {
    public void update(ArrayList<? extends Growable> children);
    public void update(Growable g);
}
