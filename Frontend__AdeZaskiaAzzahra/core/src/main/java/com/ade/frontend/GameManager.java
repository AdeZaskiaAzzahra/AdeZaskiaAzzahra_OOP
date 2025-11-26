package com.ade.frontend;

import com.ade.frontend.observers.Observer;
import com.ade.frontend.observers.ScoreManager;
import com.ade.frontend.services.BackendService;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;


public class GameManager {
    private static GameManager instance;

    // Ganti score menjadi scoreManager
    private ScoreManager scoreManager;
    private boolean gameActive;

    private BackendService backendService;
    private String currentPlayerId = null;
    private int coinsCollected = 0;

    // Constructor private untuk Singleton
    private GameManager() {
        scoreManager = new ScoreManager();
        gameActive = false;

        backendService = new BackendService();
    }


    // Singleton Pattern: hanya satu instance GameManager
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void registerPlayer(String username) {
        backendService.createPlayer(username, new BackendService.RequestCallback() {

          @Override
           public void onSuccess(String response) {
               try {
                   JsonValue json = new JsonReader().parse(response);
                   currentPlayerId = json.getString("playerId");

                   Gdx.app.log("GameManager", "Player Registered! ID = " + currentPlayerId);
               } catch (Exception e) {
                   Gdx.app.error("GameManager", "Failed parsing playerId: " + e.getMessage());
               }
           }

           @Override
           public void onError(String error) {
               Gdx.app.error("GameManager", "Register Player Failed: " + error);
           }
       });

    }

    public void startGame() {
        scoreManager.setScore(0);
        gameActive = true;

        coinsCollected = 0;
        System.out.println("Game Started!");
    }

    public void setScore(int distance) {
        if (gameActive) {
            scoreManager.setScore(distance);
        }
    }

    public int getScore() {
       return scoreManager.getScore();
   }

   // === Instruksi No.12: Getter coins ===
       public int getCoins() {
       return coinsCollected;
   }

   // === Instruksi No.10: addCoin() ===
       public void addCoin() {
       coinsCollected++;
       Gdx.app.log("COIN", "COIN COLLECTED! Total: " + coinsCollected);
   }

   // === Instruksi No.9: endGame() ===
       public void endGame() {

       if (currentPlayerId == null) {
           Gdx.app.error("GameManager", "Cannot submit score: Player ID is null!");
           return;
       }

       int distance = scoreManager.getScore();
       int finalScore = distance + (coinsCollected * 10);

       backendService.submitScore(
           currentPlayerId,
           finalScore,
           coinsCollected,
           distance,
           new BackendService.RequestCallback() {

               @Override
               public void onSuccess(String response) {
                   Gdx.app.log("GameManager",
                                                   "Score Submitted Successfully! Response: " + response);
                   }
                   @Override
                   public void onError(String error) {
                       Gdx.app.error("GameManager", "Submit Score Failed: " + error);
                   }
               }
       );
   }

   // Observer Pattern
       public void addObserver(Observer observer) {
       scoreManager.addObserver(observer);
   }

   public void removeObserver(Observer observer) {
       scoreManager.removeObserver(observer);
   }

       public void resetGame() {
       scoreManager.setScore(0);
       gameActive = false;
       System.out.println("Game Reset!");
   }
}
