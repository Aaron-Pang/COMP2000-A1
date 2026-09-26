package growables;

import java.util.ArrayList;

public interface GrowableSubject {
    public void registerObserver(GrowableObserver o);
    public void removeObserver(GrowableObserver o);
    public void notifyObserversSpread(ArrayList<? extends Growable> children);
    public void notifyObserversDie();
}
