package com.ade.backend.service;

import com.ade.backend.model.Score;
import com.ade.backend.repository.ScoreRepository;
import com.ade.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

/**
 * Silahkan tulis kode dibawah ini
 */
@Transactional
    public Score createScore(Score score){
    UUID playerId = score.getPlayerId();
    if (!playerRepository.existsById(playerId)) {
        throw new RuntimeException("Player not found with ID: " + playerId);
    }
    Score savedScore = scoreRepository.save(score);
    playerService.updatePlayerStats(
            playerId,
            score.getValue(),
            score.getCoinsCollected(),
            score.getDistanceTravelled()
    );
    return savedScore;
}

public Optional<Score>getScoreById(UUID scoreId){
    return scoreRepository.findById(scoreId);
}

public List<Score> getAllScores(){
    return scoreRepository.findAll();
}

public List<Score> getScoresByPlayerId(UUID playerId) {
    return scoreRepository.findByPlayerId(playerId);
}

public List<Score> getScoresByPlayerIdOrderByValue(UUID playerId) {
    return scoreRepository.findByPlayerIdOrderByValueDesc(playerId);
}

public List<Score> getLeaderboard(int limit) {
    return scoreRepository.findTopScores(limit);
}

}

public List<Score> getHighestScoreByPlayerId(UUID playerId){
    return scoreRepository.findHighestScoreByPlayerId(playerId);
    //bikin  cek apakah daftar tersebut kosong (isEmpty()). Jika ya, kembalikan
    //Optional.empty(). Jika tidak kosong kembalikan Optional.of() dari elemen pertama daftar
    //(scores.get(0)).
}

public List<Score> getScoresAboveValue (Integer minValue){
    return scoreRepository.findByValueGreaterThan(minValue);
}

public List<Score> getRecentScores {
    return scoreRepository. findAllByOrderByCreatedAtDesc();
}

public Integer  getTotalCoinsByPlayerId (UUID playerId) {
    return scoreRepository.getTotalCoinsByPlayerId(playerId);
    if (getTotalCoinsByPlayerId() == null) {
        return 0;
    } else {
        return getTotalCoinsByPlayerId (UUID playerId);
    }
}

public Integer  getTotalDistanceByPlayerId  (UUID playerId){
    return scoreRepository.getTotalDistanceByPlayerId(playerId);
    if (getTotalCoinsByPlayerId() == null) {
        return 0;
    } else {
        return getTotalDistanceByPlayerId (UUID playerId);
    }
}

public Score updateScore (UUID  scoreId, Score updatedScore ){
    if(!scoreRepository.findById(scoreId)) {
        throw new RuntimeException("Score not found with ID: " + scoreId);
    }

}

public deleteScore(UUID scoreId){
   return scoreRepository.deleteById(scoreId);
}

public void deleteScoresByPlayerId (UUID playerId){
    scoreRepository.findByPlayerId(playerId)
    scoreRepository.deleteAll()
}



