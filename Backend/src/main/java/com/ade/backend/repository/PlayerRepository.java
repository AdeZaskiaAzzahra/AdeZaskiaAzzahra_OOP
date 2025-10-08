package com.ade.backend.repository;

import com.ade.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {

    boolean existsByUsername(String username);

    Optional<Player> findByUsername(String username);

    @Query("SELECT p FROM Player p ORDER BY p.highScore DESC")
    List<Player> findTopPlayersByHighScore(int limit);

    List<Player> findAllByOrderByTotalCoinsDesc();

    List<Player> findAllByOrderByTotalDistanceTravelledDesc();
}
