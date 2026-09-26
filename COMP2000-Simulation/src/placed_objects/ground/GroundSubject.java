package placed_objects.ground;

import growables.Growable;

public interface GroundSubject {
    public void registerObserver(GroundObserver o);
    public void removeObserver(GroundObserver o);
    public void notifyObservers(Growable g);
}
