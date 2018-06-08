package org.interior.controller;

import org.interior.riot.bo.ChampionApi;
import org.interior.riot.model.Champion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @Autowired
    private ChampionApi championApi;

    @GetMapping(value = "/championInfo", produces = "application/json")
    public Champion test() {
        Champion champion = championApi.getChampion();
        return champion;
    }
}
