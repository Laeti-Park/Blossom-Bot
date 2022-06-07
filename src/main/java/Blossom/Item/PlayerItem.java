package Blossom.Item;

public class PlayerItem {
    private String tag;
    private String name;
    private String nowTrophy;
    private String maxTrophy;
    private String exp;
    private String tripleMode;
    private String soloMode;
    private String duoMode;
    private String rank25;
    private String rank30;
    private String rank35;
    private String club;

    public PlayerItem(String tag, String name,
                      String nowTrophy, String maxTrophy,
                      String exp, String tripleMode, String soloMode, String duoMode,
                      String rank25, String rank30, String rank35, String club) {
        this.tag = tag;
        this.name = name;
        this.nowTrophy = nowTrophy;
        this.maxTrophy = maxTrophy;
        this.exp = exp;
        this.tripleMode = tripleMode;
        this.soloMode = soloMode;
        this.duoMode = duoMode;
        this.rank25 = rank25;
        this.rank30 = rank30;
        this.rank35 = rank35;
        this.club = club;
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public String getNowTrophy() {
        return nowTrophy;
    }

    public String getMaxTrophy() {
        return maxTrophy;
    }

    public String getExp() {
        return exp;
    }

    public String getTripleMode() {
        return tripleMode;
    }

    public String getSoloMode() {
        return soloMode;
    }

    public String getDuoMode() {
        return duoMode;
    }

    public String getRank25() {
        return rank25;
    }

    public String getRank30() {
        return rank30;
    }

    public String getRank35() {
        return rank35;
    }

    public String getClub() {
        return club;
    }

    @Override
    public boolean equals(Object obj) {
        PlayerItem input = (PlayerItem) obj;

        if (input.name.matches(".*" + this.name + ".*")) {
            return true;
        } else {
            return false;
        }
    }
}
