package org.interior.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.interior.repository.Api;
import org.interior.repository.ApiRepository;
import org.interior.repository.Comment;
import org.interior.repository.CommentRepository;
import org.interior.repository.riotapi.ChampionDatabaseRepository;
import org.interior.util.translation;
import org.interior.vo.SummonerSpell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.match.dto.Participant;
import net.rithms.riot.api.endpoints.match.dto.ParticipantIdentity;
import net.rithms.riot.api.endpoints.match.dto.ParticipantStats;
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
	
	@Autowired
	private CommentRepository crdao;
	
	public int cPage = 0;
	
	//키를 가져오는 메소드
	public String getApiKey(){
		Api getKey = keydao.findById(9435L).get();
		return getKey.getApi_key();
	}
	
	//챔피언 아이콘
	public String getChampImg(int size, int championCode) throws RiotApiException{
		return "<img style='margin: 2%; margin-top: 4%;;' width='"+size+"%' src='http://ddragon.leagueoflegends.com/cdn/"+VersionJson.getVersion()+"/img/champion/" 
				+ cdao.findByIndividualKey(championCode).getFull()
				+ "'>";
	}
	
	
	@GetMapping("/userProfile")
	public String userProfile(Model model, String user) throws RiotApiException {
		
		ApiConfig config;
		
		try 
		{
			config = new ApiConfig().setKey(getApiKey());
		}
		catch (NoSuchElementException e) 
		{
			return "exception/insertKey";
		}
		
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
				
				//코멘트 불러오기
				PageRequest page = PageRequest.of(cPage, 5, Direction.DESC, "commentId");
				Page<Comment> commentPage = crdao.findAllByTargetOrderByCommentIdDesc(summoner.getName(), page);
				model.addAttribute("commentList", commentPage.getContent());
				
				//페이지 갯수 가져가기
				ArrayList<Integer> cList = new ArrayList<Integer>();
				for(int i = 0 ; i < commentPage.getTotalPages() ; i++)
				{
					cList.add(i);
				}
				model.addAttribute("cPage", cList);
				
				
			}
			catch(RiotApiException e)
			{
				
				return "exception/insertKey";
			}
			catch(Exception e)
			{
				e.printStackTrace();
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
	}//userProfile
	
	
	//페이지
	@ResponseBody
	@GetMapping(value="/getCommentPage")
	public void getCommentPage(int currentPage) {
		//System.out.println("넘어왔다" + currentPage);
		cPage = currentPage;
	}
	
	
	//과거 게임 불러오기
	@ResponseBody
	@GetMapping(value="/getPastGame")
	public String getPastGame(Long accountId, int matchPage) {
		
		StringBuffer sb = new StringBuffer();
		ApiConfig config = new ApiConfig().setKey(getApiKey());
		RiotApi api = new RiotApi(config);
		try {
			//MatchList ml = api.getMatchListByAccountId(Platform.KR, accountId);
			MatchList ml = api.getMatchListByAccountId(Platform.KR, accountId, null, null, null, -1, -1, matchPage*10, (matchPage*10)+9);
			
			List<MatchReference> ml2 = ml.getMatches();
			
			for(int i = 0 ; i < 10 ; i++)
			{
				//매치 정보
				Match mcInfo = api.getMatch(Platform.KR, ml2.get(i).getGameId());
				
				//매치 참가자
				List<Participant> mcAr = mcInfo.getParticipants();
				
				//매치 참자가의 플레이어 정보
				List<ParticipantIdentity> mcPlayer = mcInfo.getParticipantIdentities();
				
				//매치 참가자의 인게임 활약
				ParticipantStats pStat = mcAr.get(i).getStats();
				
				
				//System.out.println(mcPlayer.get(0).getPlayer().getSummonerName());
				
				sb.append("<tr style='background-color: #f8f9fa; padding: 3px; margin-top: 5px; border: 1px thick white;'>");
					
					//챔피언 아이콘
					sb.append("<td style='width: 10%;'>"+getChampImg(80, ml2.get(i).getChampion())+"</td>");
					
					//KDA
					sb.append("<td style='width: 10%; text-align: center;'>" + translation.kdaFormMaker(pStat.getKills(), pStat.getDeaths(), pStat.getAssists()) + "<br><span style='padding: 4%; margin: 3%; border-radius: 3px; border: 1px solid gray; background-color: blue; color: white;'>" + translation.kdaCal(pStat.getKills(), pStat.getDeaths(), pStat.getAssists()) + "</span></td>");
					
					//게임 모드
					sb.append("<td style='width: 20%; text-align: center;'>" + translation.gameMode(mcInfo.getGameMode()) + "<br>" + translation.isWinToString(mcAr.get(i).getStats().isWin()) + "</td>");
					
					//참가자들
					sb.append("<td style='width: 40%;'>");
					for (int k = 0 ; k < mcAr.size() ; k++) {
						
						if(mcAr.get(k).getTeamId() == 100)
						{
							sb.append(getChampImg(8, mcAr.get(k).getChampionId()) + " ");
						}
						else
						{
							if(k == 5)
							{
								sb.append("<br>" + getChampImg(8, mcAr.get(k).getChampionId()) + " ");
							}
							else
							{
								sb.append(getChampImg(8, mcAr.get(k).getChampionId()) + " ");
							}
							
						}
						
					}
					sb.append("<hr></td>");
					
					
					sb.append("<td style='width: 20%;'>" + translation.epochCalculator2(ml2.get(i).getTimestamp()) + "</td>");
				sb.append("</tr>");
			}
			
			
		} catch (RiotApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	
	
}
