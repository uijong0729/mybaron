package org.interior.riot.bo;

import org.interior.riot.model.Champion;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Service
public class ChampionApi {

    // 챔피언 정보를 가져올 end point url
    final static private String END_POINT = "http://ddragon.leagueoflegends.com/cdn/8.11.1/data/en_US/champion.json";

    // 챔피언 정보를 가져와 리턴해줌
    public Champion getChampion () {
        // HTTP 통신을 위한 RestTemplate 객체
        RestTemplate restTemplate = new RestTemplate();

        // 결과를 담을 Champion (Model? VO?) 객체
        Champion champion;

        // end point 에 요청해 결과를 Champion Class 형태로 가져옴,
        // 미리 선언해둔 Champion 객체에 담음
        champion = restTemplate.getForObject(END_POINT, Champion.class);

        // Champion 객체 리턴
        return champion;
    }



}
