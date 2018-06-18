package org.interior.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.interior.repository.Api;
import org.interior.repository.ApiRepository;
import org.interior.repository.riotapi.ChampionDatabase;
import org.interior.repository.riotapi.ChampionDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import riot.ChampionJson;
import riot.ChampionList;
import riot.VersionJson;

@Controller
public class IndexController {
	
	@Autowired
	private ApiRepository keyDAO;
	
	@Autowired
	private ChampionDatabaseRepository cdao;
	
	//초기설정은 홈에서 이뤄짐
	@GetMapping("")
	public String home() {
		
		//현재버전
		String ar = VersionJson.getVersion();
		
		String[] keys = ChampionJson.ChampionArray();
		int size = cdao.findAll().size();
		
		
		//챔피언 DB사빕
		if(size < 1 || size < keys.length || cdao.findByVersion(VersionJson.getVersion()).size() < 1)
		{
			ChampionList map = ChampionJson.getChampion();
			cdao.deleteAll();
			
			for (String string : keys) {
				
				ChampionDatabase cd = new ChampionDatabase(
						map.getData().get(string).getKey(),
						map.getData().get(string).getName(),
						map.getData().get(string).getImage().getFull(),
						map.getData().get(string).getVersion()
						);
				
				cdao.save(cd);
				System.out.println("저장완료");
			}
		}

		return "index";
	}
	
	@PostMapping("/insertKey")	
	public String insertKey(Api key) {
		
		if(key.getId() == 9435L)
		{
			Api newKey = new Api(9435L, key.getApi_key());
			keyDAO.save(newKey);
			System.out.println("키 교체 = " + newKey);
		}
		return "index";
	}
	@GetMapping("/api")
	public String api() {
		return "exception/insertKey";
	}
	
	

}
