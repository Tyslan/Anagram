package observerPattern;

/**
 * Created by Lorenz on 15/11/14.
 */
public interface Subject {
    public void addObserver(Observer O);
    public void removeObserver(Observer O);
    public void notifyObservers();
}
