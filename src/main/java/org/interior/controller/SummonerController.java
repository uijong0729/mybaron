package org.interior.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.interior.util.InstanceData;
import org.interior.util.translation;
import org.interior.vo.SummonerSpell;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.champion.dto.ChampionList;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.Participant;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameInfo;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameParticipant;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

/*
 * http://taycaldwell.com/riot-api-java/doc/
 * 
 */

@Controller
public class SummonerController {
	
	//초기설정
	ApiConfig config = new ApiConfig().setKey(InstanceData.key);
	RiotApi api = new RiotApi(config);
	
	
	@GetMapping("/userProfile")
	public String userProfile(Model model, String user) throws RiotApiException {
		
		//소환사
		try 
		{
			Summoner summoner = api.getSummonerByName(Platform.KR, user);
			model.addAttribute("summoner", summoner);
			model.addAttribute("iconImg", translation.getIconCode(summoner.getProfileIconId()));
			
			
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
				List<Integer> champIcon1 = new ArrayList<Integer>();
				List<Integer> champIcon2 = new ArrayList<Integer>();
				
				for (CurrentGameParticipant lst : list) {
					
					SummonerSpell sp = new SummonerSpell(translation.getSpellImg(lst.getSpell1Id()), translation.getSpellImg(lst.getSpell2Id()));
					lst.getChampionId();
					
					if(lst.getTeamId() == 100)
					{
						team1.add(lst);
						champIcon1.add(lst.getChampionId());
						spellIcon1.add(sp);
					}
					else
					{
						team2.add(lst);
						champIcon2.add(lst.getChampionId());
						spellIcon2.add(sp);
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
				e.printStackTrace();
				model.addAttribute("gameInfo_Time", "현재 게임 중이 아닙니다.");
				model.addAttribute("gameInfo_Mode", "게임 중인 경우만 정보가 표시됩니다.");
			}
			
		}
		catch(Exception e)
		{
			model.addAttribute("summoner", "");
			return "userprofile";
		}
					
		return "userprofile";
	}
	
	@ResponseBody
	@GetMapping("/getIcon")
	public String getIcon(String[] arr) {
		System.out.println(arr);
		for (String string : arr) {
			System.out.println(string);
		}
		
		return "";
	}
	
	
}
