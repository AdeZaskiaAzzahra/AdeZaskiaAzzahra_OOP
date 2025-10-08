package com.ade.backend.service;

import com.ade.backend.model.Player;
import com.ade.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player createPlayer(Player player) {
        if (playerRepository.existsByUsername(player.getUsername())) {
            throw new RuntimeException("Username already exists: " + player.getUsername());
        }
        return playerRepository.save(player);
    }

    public Optional<Player> getPlayerById(UUID playerId) {
        return playerRepository.findById(playerId);
    }

    public Optional<Player> getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player updatePlayer(UUID playerId, Player updatedPlayer) {
        Player existingPlayer = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found with ID: " + playerId));

        if (updatedPlayer.getUsername() != null) {
            if (!existingPlayer.getUsername().equals(updatedPlayer.getUsername())
                    && playerRepository.existsByUsername(updatedPlayer.getUsername())) {
                throw new RuntimeException("Username already exists: " + updatedPlayer.getUsername());
            }
            existingPlayer.setUsername(updatedPlayer.getUsername());
        }

        if (updatedPlayer.getHighScore() != null) {
            existingPlayer.setHighScore(updatedPlayer.getHighScore());
        }

        if (updatedPlayer.getTotalCoins() != null) {
            existingPlayer.setTotalCoins(updatedPlayer.getTotalCoins());
        }

        if (updatedPlayer.getTotalDistanceTravelled() != null) {
            existingPlayer.setTotalDistanceTravelled(updatedPlayer.getTotalDistanceTravelled());
        }

        return playerRepository.save(existingPlayer);
    }

    public void deletePlayer(UUID playerId) {
        if (!playerRepository.existsById(playerId)) {
            throw new RuntimeException("Player not found with ID: " + playerId);
        }
        playerRepository.deleteById(playerId);
    }

    public void deletePlayerByUsername(String username) {
        Player player = playerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Player not found with username: " + username));
        playerRepository.delete(player);
    }

    public Player updatePlayerStats(UUID playerId, Integer scoreValue, Integer coinsCollected, Integer distanceTravelled) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found with ID: " + playerId));

        player.updateHighScore(scoreValue);
        player.addCoins(coinsCollected);
        player.addDistance(distanceTravelled);

        return playerRepository.save(player);
    }

    public List<Player> getLeaderboardByHighScore(int limit) {
        return playerRepository.findTopPlayersByHighScore(limit);
    }

    public List<Player> getLeaderboardByTotalCoins() {
        return playerRepository.findAllByOrderByTotalCoinsDesc();
    }

    public List<Player> getLeaderboardByTotalDistance() {
        return playerRepository.findAllByOrderByTotalDistanceTravelledDesc();
    }

    public boolean isUsernameExists(String username) {
        return playerRepository.existsByUsername(username);
    }
}
