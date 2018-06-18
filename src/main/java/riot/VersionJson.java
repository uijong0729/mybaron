package riot;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VersionJson {
		
	
	 // 챔피언 정보를 가져올 end point url
    final static private String END_POINT = "https://ddragon.leagueoflegends.com/api/versions.json";

    // 챔피언 정보를 가져와 리턴해줌
    @SuppressWarnings("unchecked")
	public static String getVersion() {
       
        RestTemplate restTemplate = new RestTemplate();

        ArrayList<String> version;

        version = restTemplate.getForObject(END_POINT, ArrayList.class);

        return version.get(0);
    }
}
