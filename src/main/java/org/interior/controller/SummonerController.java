package org.interior.controller;

import org.interior.util.Apikey;
import org.interior.util.translation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameInfo;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

@Controller
public class SummonerController {
	
	//초기설정
	ApiConfig config = new ApiConfig().setKey(Apikey.key);
	RiotApi api = new RiotApi(config);
	
	@GetMapping("/userProfile")
	public String userProfile(Model model, String user) throws RiotApiException {
		
		//소환사
		try 
		{
			Summoner summoner = api.getSummonerByName(Platform.KR, user);
			model.addAttribute("summoner", summoner);

			//현재 게임
			try 
			{
				CurrentGameInfo activity = api.getActiveGameBySummoner(Platform.KR, summoner.getId());
				
				String printTime = translation.epochCalculator(activity.getGameStartTime());
				String gameMode = translation.gameMode(activity.getGameMode());
				
				model.addAttribute("gameInfo_Time", "시작 시간 : " + printTime + " 시작");
				model.addAttribute("gameInfo_Mode", "게임 모드 : " + gameMode);
				
			}
			catch(Exception e)
			{
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
	
	
	@ResponseBody
	@GetMapping("/getIconCode")
	public String getIconCode() throws RiotApiException{
		
		
		
		return "558";
	}

}
