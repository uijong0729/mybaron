package org.interior.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.interior.repository.Api;
import org.interior.repository.ApiRepository;
import org.interior.repository.riotapi.ChampionDatabaseRepository;
import org.interior.util.InstanceData;
import org.interior.util.translation;
import org.interior.vo.SummonerSpell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameInfo;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameParticipant;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
import riot.VersionJson;

/*
 * http://taycaldwell.com/riot-api-java/doc/
 * 
 */

@Controller
public class SummonerController {
	
	@Autowired
	ApiRepository keydao;
	
	@Autowired
	private ChampionDatabaseRepository cdao;
	
	Long key = 9435L;
	
	@GetMapping("/userProfile")
	public String userProfile(Model model, String user) throws RiotApiException {
		
		Api getKey = null;
		try 
		{
			//key
			getKey = keydao.findById(9435L).get();
			//System.out.println("키 로드 = " + getKey);
		}
		catch (NoSuchElementException e) 
		{
			//e.printStackTrace();
			return "exception/insertKey";
		}
		
		ApiConfig config = new ApiConfig().setKey(getKey.getApi_key());
		RiotApi api = new RiotApi(config);
		
		
		//소환사
		try 
		{
			Summoner summoner = null;
			try 
			{
				summoner = api.getSummonerByName(Platform.KR, user);
				model.addAttribute("summoner", summoner);
				model.addAttribute("iconImg", translation.getIconCode(summoner.getProfileIconId()));
			}
			catch(RiotApiException e)
			{
				return "exception/insertKey";
			}
			
			//현재 게임
			try 
			{
				//게임
				CurrentGameInfo activity = api.getActiveGameBySummoner(Platform.KR, summoner.getId());
				
				String printTime = translation.epochCalculator(activity.getGameStartTime());
				String gameMode = translation.gameMode(activity.getGameMode());
				List<CurrentGameParticipant> list = activity.getParticipants();
				List<CurrentGameParticipant> team1 = new ArrayList<CurrentGameParticipant>();
				List<CurrentGameParticipant> team2 = new ArrayList<CurrentGameParticipant>();
				List<SummonerSpell> spellIcon1 = new ArrayList<SummonerSpell>();
				List<SummonerSpell> spellIcon2 = new ArrayList<SummonerSpell>();
				List<String> champIcon1 = new ArrayList<String>();
				List<String> champIcon2 = new ArrayList<String>();
				
				for (CurrentGameParticipant lst : list) {
					
					SummonerSpell sp = new SummonerSpell(translation.getSpellImg(lst.getSpell1Id()), translation.getSpellImg(lst.getSpell2Id()));
					String getFull = cdao.findByIndividualKey(lst.getChampionId()).getFull();
					
					if(lst.getTeamId() == 100)
					{
						team1.add(lst);
						spellIcon1.add(sp);
						
						champIcon1.add("http://ddragon.leagueoflegends.com/cdn/"+VersionJson.getVersion()+"/img/champion/"+getFull);
					}
					else
					{
						team2.add(lst);
						spellIcon2.add(sp);
						champIcon2.add("http://ddragon.leagueoflegends.com/cdn/"+VersionJson.getVersion()+"/img/champion/"+getFull);
					}
				}
				
				model.addAttribute("gameInfo_Time", "시작 시간 : " + printTime + " 시작");
				model.addAttribute("gameInfo_Mode", "게임 모드 : " + gameMode);
				model.addAttribute("gameInfo_Team1", team1);
				model.addAttribute("gameInfo_Team2", team2);
				model.addAttribute("gameInfo_Spell1", spellIcon1);
				model.addAttribute("gameInfo_Spell2", spellIcon2);
				model.addAttribute("gameInfo_Champ1", champIcon1);
				model.addAttribute("gameInfo_Champ2", champIcon2);
			}
			catch(Exception e)
			{
				//e.printStackTrace();
				model.addAttribute("gameInfo_Time", "현재 게임 중이 아닙니다.");
				model.addAttribute("gameInfo_Mode", "게임 중인 경우만 정보가 표시됩니다.");
			}
			
		}
		catch(Exception e)
		{
			return "userprofile";
		}
					
		return "userprofile";
	}
	
	//json데이터
	@ResponseBody
	@GetMapping("/getIcon")
	public String[] getIcon(String[] arr) {
		
		
		return arr;
	}
	
	
}
