package org.interior.util;

import java.io.FileReader;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonData {

		//챔피언 코드를 이름으로 변환
		public String parseJson() {
			JSONParser parser = new JSONParser();
			String path = this.getClass().getResource("/").getPath();

			try 
			{
				Object obj = parser.parse(new FileReader(path + "champion.json")); 
				JSONObject jsonObject = (JSONObject) obj; 
				
				System.out.println(jsonObject);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			return "";
		}
		
		
}
