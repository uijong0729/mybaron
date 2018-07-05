package org.interior.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.interior.repository.Api;
import org.interior.repository.ApiRepository;
import org.interior.repository.Comment;
import org.interior.repository.CommentRepository;
import org.interior.repository.riotapi.ChampionDatabaseRepository;
import org.interior.util.Utility;
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
	
	//멤버변수
	public int cPage = 0;
	public String version = VersionJson.getVersion();
	public Long getStartTime = 0L;
	
	//키를 가져오는 메소드
	public String getApiKey(){
		Api getKey = keydao.findById(9435L).get();
		return getKey.getApi_key();
	}
	
	//챔피언 아이콘
	public String getChampImg(int size, int championCode) throws RiotApiException{
		StringBuffer sb = new StringBuffer();
		
		sb.append("<img style='margin: 2%; margin-top: 4%;;' width='");
		sb.append(size);
		sb.append("%' src='http://ddragon.leagueoflegends.com/cdn/");
		sb.append(version);
		sb.append("/img/champion/");
		sb.append(cdao.findByIndividualKey(championCode).getFull());
		sb.append("'>");
		
		return sb.toString();
	}
	
	//챔피언 아이콘 + 캡션
	public String getChampImg(int size, int championCode, String caption) throws RiotApiException{
		StringBuffer sb = new StringBuffer();
		
		sb.append("<figure style='text-align: center; display: inline-block; width: 19%;'>");
		sb.append("<img style='margin: 2%; margin-top: 4%;;' width='");
		sb.append(size);
		sb.append("%' src='http://ddragon.leagueoflegends.com/cdn/");
		sb.append(version);
		sb.append("/img/champion/");
		sb.append(cdao.findByIndividualKey(championCode).getFull());
		sb.append("'>");
		sb.append("<figcaption style='text-align: center; display: block;'>");
		sb.append("<a style='font-size:"+ (size/4) + "%; target='_blank' href='userProfile?user=");
		sb.append(caption);
		sb.append("'>");
		sb.append(caption);
		sb.append("</a>");
		sb.append("</figcaption>");
		sb.append("</figure>");
		
		return sb.toString();
	}
	
	
	//검색시 매핑
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
				model.addAttribute("iconImg", Utility.getIconCode(summoner.getProfileIconId()));
				
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
				
				String printTime = Utility.epochCalculator(activity.getGameStartTime());
				String gameMode = Utility.gameMode(activity.getGameMode());
				List<CurrentGameParticipant> list = activity.getParticipants();
				List<CurrentGameParticipant> team1 = new ArrayList<CurrentGameParticipant>();
				List<CurrentGameParticipant> team2 = new ArrayList<CurrentGameParticipant>();
				List<SummonerSpell> spellIcon1 = new ArrayList<SummonerSpell>();
				List<SummonerSpell> spellIcon2 = new ArrayList<SummonerSpell>();
				List<String> champIcon1 = new ArrayList<String>();
				List<String> champIcon2 = new ArrayList<String>();
				
				for (CurrentGameParticipant lst : list) {
					
					SummonerSpell sp = new SummonerSpell(Utility.getSpellImg(lst.getSpell1Id()), Utility.getSpellImg(lst.getSpell2Id()));
					String getFull = cdao.findByIndividualKey(lst.getChampionId()).getFull();
					
					if(lst.getTeamId() == 100)
					{
						team1.add(lst);
						spellIcon1.add(sp);
						
						champIcon1.add("http://ddragon.leagueoflegends.com/cdn/"+version+"/img/champion/"+getFull);
					}
					else
					{
						team2.add(lst);
						spellIcon2.add(sp);
						champIcon2.add("http://ddragon.leagueoflegends.com/cdn/"+version+"/img/champion/"+getFull);
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
				
				//현재시간 삽입
				getStartTime = activity.getGameStartTime();
				
				//model.addAttribute("startTime", activity.getGameStartTime());
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
		//long start = System.currentTimeMillis(); //시작하는 시점 계산
		ApiConfig config = new ApiConfig().setKey(getApiKey());
		RiotApi api = new RiotApi(config);
		
		try {
			//MatchList ml = api.getMatchListByAccountId(Platform.KR, accountId);
			MatchList ml = api.getMatchListByAccountId(Platform.KR, accountId, null, null, null, -1, -1, matchPage*10, (matchPage*10)+9);
			
			List<MatchReference> ml2 = ml.getMatches();
			
			for(int i = 0 ; i < ml2.size() ; i++)
			{
				//매치 정보
				Match mcInfo = api.getMatch(Platform.KR, ml2.get(i).getGameId());
				
				//매치 참가자
				List<Participant> mcAr = mcInfo.getParticipants();
		
				//매치 참자가의 플레이어 정보
				List<ParticipantIdentity> mcPlayer = mcInfo.getParticipantIdentities();
				//System.out.println(mcPlayer.get(0).getPlayer().getSummonerName());
				
				//매치 참가자의 index
				int itsMe = 0;
				
				//매치 참가자 중 누가 푸르밀인가?
				for(int b = 0 ; b < mcAr.size() ; b++)
				{
					if(mcInfo.getParticipantIdentities().get(b).getPlayer().getAccountId() == accountId)
					{
						itsMe = b;
						break;
					}
				}
				
				//System.out.println(mcPlayer.get(itsMe).getPlayer().getSummonerName());
				
				//매치 참가자의 인게임 활약
				ParticipantStats pStat = mcAr.get(itsMe).getStats(); 
				boolean isWin = pStat.isWin();
				
				
				if(isWin)
				{
					sb.append("<tr style='background-color: #e0ffff; padding: 3px; margin-top: 5px;'>");
				}
				else
				{
					sb.append("<tr style='background-color: #f8f9fa; padding: 3px; margin-top: 5px;'>");
				}
				
				
					//챔피언 아이콘
					sb.append("<td style='width: 10%;'>");
					sb.append(getChampImg(80, ml2.get(i).getChampion()));
					sb.append("</td>");
					
					//KDA
					sb.append("<td style='width: 8%; text-align: center;'>");
					sb.append(Utility.kdaFormMaker(pStat.getKills(), pStat.getDeaths(), pStat.getAssists()));
					sb.append("<br><span style='padding: 4%; margin: 3%; border-radius: 3px; border: 1px solid gray; background-color: blue; color: white;'>");
					sb.append(Utility.kdaCal(pStat.getKills(), pStat.getDeaths(), pStat.getAssists()));
					sb.append("</span></td>");
					
					//게임 모드
					sb.append("<td style='width: 12%; text-align: center;'>");
					sb.append(Utility.gameMode(mcInfo.getGameMode()));
					sb.append("<br>");
					sb.append(Utility.isWinToString(isWin));
					sb.append("</td>");
					
					//참가자들
					sb.append("<td style='width: 57%;'>");
					
					
					for (int k = 0 ; k < mcAr.size() ; k++) {
						
						if(k != 5)
						{
							//getChampImg(이미지크기, 챔피언ID, 소환사이름)
							sb.append(getChampImg(40, mcAr.get(k).getChampionId(), mcPlayer.get(k).getPlayer().getSummonerName()));
							sb.append(" ");
						}
						else
						{
							sb.append("<hr>");
							sb.append(getChampImg(40, mcAr.get(k).getChampionId(), mcPlayer.get(k).getPlayer().getSummonerName()));
							sb.append(" ");
						}
						
					}
					
					
					sb.append("<hr style='border: 2px solid gray;'></td><td style='width: 12%; text-align: center;'>");
					sb.append(Utility.epochCalculator2(ml2.get(i).getTimestamp()));
					sb.append("</td>");
					
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
		
		
		//long end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
		//System.out.println( "실행 시간 : " + ( end - start )/1000.0 +"초"); //실행 시간 계산 및 출력

		
		return sb.toString();
	}
	
	
	@ResponseBody
	@GetMapping(value="/init")
	public Long init() {
		
		Long init = getStartTime;
		
		return init;
	}
	
	
	
	
	
	
	
}
