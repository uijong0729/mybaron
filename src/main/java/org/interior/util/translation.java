package org.interior.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.interior.repository.riotapi.ChampionDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import net.rithms.riot.api.RiotApiException;
import riot.VersionJson;

public class translation {
	
	
	//epoch시간을 시 분 초로 리턴함
	public static String epochCalculator(Long time)
	{
		Instant now = Instant.ofEpochMilli(time);
		OffsetDateTime gameTime = OffsetDateTime.ofInstant(now, ZoneId.systemDefault()); 
		
		String printTime = gameTime.getHour() + "시 " + mm(gameTime.getMinute()) + "분 " + mm(gameTime.getSecond()) + "초";
		
		return printTime;
	}
	
	//epoch시간을 날짜 시간 분으로 환산
	public static String epochCalculator2(Long time)
	{
		Instant now = Instant.ofEpochMilli(time);
		OffsetDateTime gameTime = OffsetDateTime.ofInstant(now, ZoneId.systemDefault()); 
		
		String printTime = gameTime.getMonth().getValue() + "월 " + gameTime.getDayOfMonth() + "일 <br>" + gameTime.getHour() + "시  " + mm(gameTime.getMinute()) + "분 ";
		
		return printTime;
	}
	
	//00처리
	private static String mm(int minute) {
		
		if(minute < 10)
		{
			return "0" + minute; 
		}
		else
		{
			return minute + "";
		}
	}
	
	//게임 모드를 한글로 바꿈
	public static String gameMode(String mode) {
		
		switch (mode) {
		case "CLASSIC":
			return "소환사의 협곡";
		case "ARAM":
			return "칼바람 나락";
		default:
			return mode;
		}
	}
	
	
	//게임 아이콘
	public static String getIconCode(int iconCode) throws RiotApiException{
		String iconTag = "<img width='15%' src='http://opgg-static.akamaized.net/images/profile_icons/profileIcon" 
					+ iconCode 
					+ ".jpg'>";
		
		return iconTag;
	}
	
	//스펠 아이콘
	/*
	 */
	public static String getSpellImg(int iconCode) throws RiotApiException{
		
		String spellName = "";
		
		switch(iconCode)
		{
			case 1: spellName = "SummonerBoost";	break;
			case 4: spellName = "SummonerFlash";	break;
			case 3: spellName = "SummonerExhaust";	break;
			case 6: spellName = "SummonerHaste";	break;
			case 7: spellName = "SummonerHeal";		break;
			case 11: spellName = "SummonerSmite";	break;
			case 12: spellName = "SummonerTeleport";	break;
			case 13: spellName = "SummonerMana";	break;
			case 14: spellName = "SummonerDot";	break;
			case 32: spellName = "SummonerSnowball";	break;
			case 21: spellName = "SummonerBarrier";	break;
			default: 
		}
		String iconTag = "<img width='25%' src='summonerSpell/" 
					+ spellName
					+ ".png'>";
		
		return iconTag;
	}
	

	
	//현재시간 계산
	public static String currentTime() {
		
		long time = System.currentTimeMillis(); 

		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		return dayTime.format(new Date(time));
	}
	
	public static String isWinToString(boolean isWin) {
		
		if(isWin)
		{
			return "<span style='color: blue;'>승리</span>";
		}
		else
		{
			return "<span style='color: red;'>패배</span>";
		}
	}
	
	

	
}
