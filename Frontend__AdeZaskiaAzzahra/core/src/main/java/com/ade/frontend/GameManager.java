package com.ade.frontend;

import com.ade.frontend.observers.Observer;
import com.ade.frontend.observers.ScoreManager;

public class GameManager {
    private static GameManager instance;

    // Ganti score menjadi scoreManager
    private ScoreManager scoreManager;
    private boolean gameActive;

    // Constructor private untuk Singleton
    private GameManager() {
        scoreManager = new ScoreManager();
        gameActive = false;
    }

    // Singleton Pattern: hanya satu instance GameManager
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startGame() {
        scoreManager.setScore(0);
        gameActive = true;
        System.out.println("Game Started!");
    }

    // Gunakan ScoreManager untuk mengatur skor
    public void setScore(int newScore) {
        if (gameActive) {
            scoreManager.setScore(newScore);
        }
    }

    public int getScore() {
        return scoreManager.getScore();
    }

    // Tambahkan dukungan Observer Pattern
    public void addObserver(Observer observer) {
        scoreManager.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        scoreManager.removeObserver(observer);
    }

    // Optional: method tambahan untuk reset
    public void resetGame() {
        scoreManager.setScore(0);
        gameActive = false;
        System.out.println("Game Reset!");
    }
}
