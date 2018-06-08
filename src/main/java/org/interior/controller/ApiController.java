package org.interior.controller;

import org.interior.riot.bo.ChampionApi;
import org.interior.riot.model.Champion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Rest API 를 위한 Controller, JSON 응답만 내려주기때문에 RestController Annotation 사용
@RestController
public class ApiController {

    // Champion 정보를 가져오기 위한 Method 가 있는 ChampionApi (BO) 객체를 선언, 의존성 주입
    @Autowired
    private ChampionApi championApi;

    // /championInfo end poiont 에 url mapping, json 응답을 할 것으로
    // produces에 "application/json" 추가 ( HTTP Response Header 에 Content-Type: application/json 추가됨
    @GetMapping(value = "/championInfo", produces = "application/json")
    public Champion test() {
        // Champion (Model 또는 VO?) 객체 선언
        Champion champion;

        // getChampion() Method 로 champion 정보를 가져와 객체에 담음
        champion = championApi.getChampion();

        // Champion 객체를 리턴
        return champion;
    }
}
