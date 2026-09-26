package growables;

import java.util.ArrayList;

public interface GrowableSubject {
    public void registerObserver(GrowableObserver o);
    public void removeObserver(GrowableObserver o);
    public void notifyObservers(ArrayList<? extends Growable> children);
}
