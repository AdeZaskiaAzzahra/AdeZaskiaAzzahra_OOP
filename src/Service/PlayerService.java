package Service;

import Model.Player;
import Repository.PlayerRepository ;
import java.util.*;

public abstract class PlayerService {
    private PlayerRepository playerRepository;
    public PlayerService(PlayerRepository playerRepository);
    this.playerRepository = PlayerRepository;
}

public boolean existsByUsername(String username) {
    return playerRepository.existsByUsername(username);
}

public Player createPlayer(Player player) {
    if (existsByUsername(player.getUsername()))
        throw new
}

public getPlayerById(UUID playerId) {
    return playerId
}

public getPlayerByUsername(String username){

}

public getAllPlayers(){
    return
}

public updatePlayer(UUID playerId, Player updatedPlayer){

}

public deletePlayer(UUID playerId) {

}

public deletePlayerByUsername(String username) {

}

public updatePlayerStats(UUID playerId, int scoreValue, int coinsCollected, int
        distanceTravelled) {

}

public getLeaderboardByHighScore(int limit) {

}

public getLeaderboardByTotalCoins() {

}

public getLeaderboardByTotalDistance() {

}


