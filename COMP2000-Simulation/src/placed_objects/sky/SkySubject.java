package placed_objects.sky;

public interface SkySubject {
    public void registerObserver(SkyObserver o);
    public void removeObserver(SkyObserver o);
    public void notifyObservers();
}
