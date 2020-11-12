package com.alp.player;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Player API
 */
@RestController
public class PlayerController {

    private final PlayerRepository playerRepository;

    PlayerController(PlayerRepository repository) {this.playerRepository = repository; }

    /**
     * GET /api/players - returns the list of all players
     */
    @GetMapping("/api/players")
    List<Player> getAllPlayers() { return playerRepository.findAll();}

    /**
     * get single player give  ID
     * @param id playerID
     * @return   player as JSON
     */
    @GetMapping("/api/players/{id}")
    Player getPlayer(@PathVariable String id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
    }

    /**
     *  Increase player's weight
     * @param id playerID
     * @return   updated player
     */
    @Transactional
    @PutMapping("/api/players/{id}/weight")
    Player increaseWeight(@PathVariable String id) {
        return playerRepository.findById(id)
                .map(p -> {
                    p.setWeight(p.getWeight() + 1);
                    return playerRepository.save(p);
                })
                .orElse(null);
    }

    /**
     *  Increase player's height
     * @param id playerID
     * @return   updated player
     */
    @Transactional
    @PutMapping("/api/players/{id}/height")
    Player increaseHeight(@PathVariable String id) {
        return playerRepository.findById(id)
                .map(p -> {
                    p.setHeight(p.getHeight() + 1);
                    return playerRepository.save(p);
                })
                .orElse(null);

    }
}
