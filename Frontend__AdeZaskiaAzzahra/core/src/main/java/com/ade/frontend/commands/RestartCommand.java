package com.ade.frontend.commands;

import com.ade.frontend.Player;
import com.ade.frontend.GameManager;

public class RestartCommand implements Command {
    private Player player;
    private GameManager gameManager;
    public RestartCommand(Player player, GameManager gameManager) {
        this.player = player;
        this.gameManager = gameManager;
    }

    @Override
    public void execute() {
        player.reset();
        gameManager.setScore(0);
    }
}
