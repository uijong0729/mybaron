package org.interior.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;


import net.rithms.riot.api.RiotApiException;

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
		StringBuffer sb = new StringBuffer();
		
		Instant now = Instant.ofEpochMilli(time);
		OffsetDateTime gameTime = OffsetDateTime.ofInstant(now, ZoneId.systemDefault()); 
		
		sb.append(gameTime.getMonth().getValue());
		sb.append("월 ");
		sb.append(gameTime.getDayOfMonth());
		sb.append("일 <br>");
		sb.append(gameTime.getHour());
		sb.append("시  ");
		sb.append(mm(gameTime.getMinute()));
		sb.append("분 ");
		
		return sb.toString();
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
	
	//승리 패배 리턴
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
	
	//kda form maker
	public static String kdaFormMaker(int kills, int deaths, int assists) {
		StringBuffer sb = new StringBuffer();
		sb.append("<span style='color: blue;'>");
		sb.append(kills);
		sb.append("</span>/<span style='color: red;'>");
		sb.append(deaths);
		sb.append("</span>/<span style='color: green;'>" );
		sb.append(assists);
		sb.append("</span>");
		return sb.toString();
	}
	
	//kda form calculator
	public static String kdaCal(int kills, int deaths, int assists) {
		
		if(deaths == 0)
		{
			if(kills != 0 || assists != 0)
			{
				return "perfect";
			}
			else
			{
				return "아무것도 못함";
			}
		}
		else
		{
			return Math.round( ((double) (kills + assists) / (double) deaths) * 100)/100.0 + "";
		}
		
	}
	
	

	
}
