package org.interior.riot.model;

import lombok.*;

import java.util.Map;

// Lombok 사용 시 깔끔해진걸 볼 수 있음
@NoArgsConstructor  // 인자가 없는 생성자를 만들어 줌
@AllArgsConstructor // 모든 인자가 포함된 생성자를 만들어 줌
@Getter @Setter     // 모든 멤버 변수에 대한 getter 와 setter 를 만들어 줌
@ToString           // Champion Class 에 대한 ToString Method 를 만들어 줌
public class Champion {
    private String type;
    private String format;
    private String version;
    private Map<String,ChampionDesc> data;

    //@Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter @Setter
    @ToString
    private static class ChampionDesc {
        private String blurb;
        private String id;
        private Image image;
        //@Id
        //@NonNull
        private int key;
        private String name;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter @Setter
    @ToString
    private static class Image {
        private String full;
        private String group;
        private String sprite;
    }
}

// Lombok 사용 전
/*public class Champion {
    private String type;
    private String format;
    private String version;
    private Map<String,ChampionDesc> data;

    @Override
    public String toString() {
        return getType() + " " + getVersion() + " " + getFormat() + " " + getData();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, ChampionDesc> getData() {
        return data;
    }

    public void setData(Map<String, ChampionDesc> data) {
        this.data = data;
    }

    public Champion() {
    }

    public Champion(String type, String format, String version, Map<String, ChampionDesc> data) {
        this.type = type;
        this.format = format;
        this.version = version;
        this.data = data;
    }

    //@Entity
    private static class ChampionDesc {
        private String blurb;
        private String id;
        private Image image;
        //@Id
        //@NonNull
        private int key;
        private String name;

        public String getBlurb() {
            return blurb;
        }

        public void setBlurb(String blurb) {
            this.blurb = blurb;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Champion.Image getImage() {
            return image;
        }

        public void setImage(Champion.Image image) {
            this.image = image;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ChampionDesc() {
        }

        private ChampionDesc(String blurb, String id, Champion.Image image, int key, String name) {
            this.blurb = blurb;
            this.id = id;
            this.image = image;
            this.key = key;
            this.name = name;
        }
    }

    private static class Image {
        private String full;
        private String group;
        private String sprite;

        public String getFull() {
            return full;
        }

        public void setFull(String full) {
            this.full = full;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getSprite() {
            return sprite;
        }

        public void setSprite(String sprite) {
            this.sprite = sprite;
        }

        public Image() {
        }

        private Image(String full, String group, String sprite) {
            this.full = full;
            this.group = group;
            this.sprite = sprite;
        }
    }
}*/