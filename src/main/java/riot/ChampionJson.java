package riot;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ChampionJson {

	    // 챔피언 정보를 가져올 end point url
	    final static private String END_POINT = "http://ddragon.leagueoflegends.com/cdn/"+VersionJson.getVersion()+"/data/en_US/champion.json";

	    // 챔피언 정보를 가져와 리턴해줌
	    public static ChampionList getChampion () {
	       
	    	// HTTP 통신을 위한 RestTemplate 객체
	        RestTemplate restTemplate = new RestTemplate();

	        // 결과를 담을 Champion (Model? VO?) 객체
	        ChampionList champion;

	        // end point 에 요청해 결과를 Champion Class 형태로 가져옴,
	        champion = restTemplate.getForObject(END_POINT, ChampionList.class);

	        // Champion 객체 리턴
	        return champion;
	    }
	    
	    
	    //챔피언의 배열
	    public static String[] ChampionArray() {
	    	
	    	return getChampion().getData().keySet().toArray(new String[0]);
	    }
	    

}
