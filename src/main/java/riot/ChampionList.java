package riot;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor  // 인자가 없는 생성자를 만들어 줌
@AllArgsConstructor // 모든 인자가 포함된 생성자를 만들어 줌
@Getter @Setter     // 모든 멤버 변수에 대한 getter 와 setter 를 만들어 줌
@ToString           // Champion Class 에 대한 ToString Method 를 만들어 줌
public class ChampionList {
	
	    private Map<String,ChampionDesc> data;

	    //@Entity
	    @NoArgsConstructor
	    @AllArgsConstructor
	    @Getter @Setter
	    @ToString
		public
	    static class ChampionDesc {
	        private String blurb;
	        private String id;
	        private Image image;
	        //@Id
	        //@NonNull
	        private int key;
	        private String name;
	        private String version;
	    }

	    @NoArgsConstructor
	    @AllArgsConstructor
	    @Getter @Setter
	    @ToString
	    public static class Image {
	        private String full;
	        private String group;
	        private String sprite;
	    }
}
