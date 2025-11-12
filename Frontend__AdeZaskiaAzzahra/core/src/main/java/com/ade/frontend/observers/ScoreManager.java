package com.ade.frontend.observers;

import java.util.ArrayList;
import java.util.List;

public class ScoreManager implements Subject {
    private List<Observer> observers;
    private int score;

    public ScoreManager() {
        observers = new ArrayList<>();
        score = 0;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

@Override
    public void notifyObservers (int score) {
        for (Observer observer : observers) {
            observer.update(score);
        }
    }

    public void setScore(int newScore) {
        if (newScore != score){
            score = newScore;
            notifyObservers(score);
        }
    }
    public int getScore() {
        return score;
    }
}
