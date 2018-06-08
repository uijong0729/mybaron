package org.interior.riot.bo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.interior.riot.model.Champion;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class ChampionApi {

    final static private String END_POINT = "http://ddragon.leagueoflegends.com/cdn/8.11.1/data/en_US/champion.json";

    public Champion getChampion () {
        RestTemplate restTemplate = new RestTemplate();
        /*ObjectMapper objectMapper = new ObjectMapper();

        String championString;
        Champion champion = new Champion();

        championString = restTemplate.getForObject(END_POINT, String.class);
        try {
            champion = objectMapper.readValue(championString, Champion.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        Champion champion = new Champion();

        champion = restTemplate.getForObject(END_POINT, Champion.class);
        return champion;
    }



}
