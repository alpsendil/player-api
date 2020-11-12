package com.alp.player;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

//@Configuration
public class DBInitializer {

    Logger log = LogManager.getLogger(DBInitializer.class);

    private static final String fileName = "/Users/alpsendil/src/player/src/main/resources/players.csv";

    // comment out so the values are not loaded more than once in DB
//    @Bean
//    CommandLineRunner initializeDatabase(PlayerRepository repository) {
//
//        return args -> {
//            File file = new File(fileName);
//
//            try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
//                while (sc.hasNextLine()) {
//                    String rawString = sc.nextLine();
//                    String[] row = rawString.split(",");
//                    Player player = playerFromCSV(row);
//                    log.info("importing Player: ["+player.getPlayerID()+"]");
//                    repository.save(player);
//                }
//            }
//            catch (IOException e) {e.printStackTrace(); }
//
//        };
//    }

    Player playerFromCSV(String[] row ) {
        Player p = new Player();
        p.setPlayerID(row[0]);
        Integer year =  row[1].length()==0 ? null : Integer.parseInt(row[1]);
        Integer month = row[2].length()==0 ? null : Integer.parseInt(row[2]);
        Integer day =   row[3].length()==0 ? null : Integer.parseInt(row[3]);
        p.setBirthYear(year);
        p.setBirthMonth(month);
        p.setBirthDay(day);
        p.setBirthCountry(row[4]);
        p.setBirthState(row[5]);
        p.setBirthCity(row[6]);

        year =  row[7].length()==0 ? null : Integer.parseInt(row[7]);
        month = row[8].length()==0 ? null : Integer.parseInt(row[8]);
        day =   row[9].length()==0 ? null : Integer.parseInt(row[9]);
        p.setDeathYear(year);
        p.setDeathMonth(month);
        p.setDeathDay(day);
        p.setDeathCountry(row[10]);
        p.setDeathState(row[11]);
        p.setDeathCity(row[12]);

        p.setNameFirst(row[13]);
        p.setNameLast(row[14]);
        p.setNameGiven(row[15]);

        Integer weight =  row[16].length()==0 ? null : Integer.parseInt(row[16]);
        Integer height =  row[17].length()==0 ? null : Integer.parseInt(row[17]);
        p.setWeight(weight);
        p.setHeight(height);
        p.setBats(row[18]);
        p.setBbthrows(row[19]);
        p.setDebut(row[20]);
        p.setFinalGame(row[21]);
        p.setRetroID(row[22]);
        p.setBbrefID(row[23]);
        return p;

    }
}
