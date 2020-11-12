package com.alp.player;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *  Unit tests for player API
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //pick random port for testing
public class getPlayerTest {

    private static final String LOCALHOST = "http://localhost:";
    private static final String URI = "/player/";

    private static final String PLAYER_ID = "aaronha01";
    private static final String FIRST_NAME = "Hank";
    private static final String LAST_NAME = "Aaron";
    private static final String GIVEN_NAME = "Henry Louis";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Find player by ID
     */
    @Test
    public void getHankAaron()  {
        String url = LOCALHOST + port + URI + PLAYER_ID;
        ResponseEntity<Player> responseEntity = restTemplate.getForEntity(url, Player.class);
        assertThat(responseEntity).isNotNull();
        Player player = responseEntity.getBody();
        if (player!=null) {
            assertThat(FIRST_NAME.equals(player.getNameFirst()));
            assertThat(LAST_NAME.equals(player.getNameLast()));
            assertThat(GIVEN_NAME.equals(player.getNameGiven()));
        }
    }

    /**
     * Test Bad ID for requesting player
     */
    @Test
    public void badPlayerID() {
        String url = LOCALHOST + port + URI + "BAD_ID";
        assertThat(restTemplate.getForEntity(url, Player.class).getStatusCode()== HttpStatus.NOT_FOUND);
    }

    /**
     * Test without ID
     */
    @Test
    public void getWithNoId() {
        String url = LOCALHOST + port + URI;
        assertThat(restTemplate.getForEntity(url, Player.class).getStatusCode()== HttpStatus.NOT_FOUND);

    }
}
