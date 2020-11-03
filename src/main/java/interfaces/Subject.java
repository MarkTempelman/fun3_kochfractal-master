package interfaces;

public interface Subject {
    void attachObserver(Observer observer);
    void notifyAllObservers();
}
