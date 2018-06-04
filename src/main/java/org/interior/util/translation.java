package org.interior.util;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class translation {
	
	
	//epoch시간을 시 분 초로 리턴함
	public static String epochCalculator(Long time)
	{
		Instant now = Instant.ofEpochMilli(time);
		OffsetDateTime gameTime = OffsetDateTime.ofInstant(now, ZoneId.systemDefault()); 
		
		String printTime = gameTime.getHour() + "시 " + mm(gameTime.getMinute()) + "분 " + mm(gameTime.getSecond()) + "초";
		
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
	
	
	
	
}
