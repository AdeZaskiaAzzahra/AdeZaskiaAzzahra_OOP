package com.ade.backend.repository;

import com.ade.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
    Optional<Player> findByUsername(String username);
    boolean existsByUsername(String username);

    @Query("SELECT p FROM Player p ORDER BY p.highscore DESC")
    List<Player> findTopPlayersByHighscore(@Param("Limit") int limit);

    List<Player> findByHighscoreGreaterThan(Integer minScore);
    List<Player> findAllByOrderByTotalCoinsDesc();

    List<Player> findAllByOrderByTotalDistanceDesc();
}
