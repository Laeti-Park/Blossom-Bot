package Blossom.Item;

import java.text.Normalizer;

public class PlayerItem {
    private final String tag;
    private final String name;
    private final String nowTrophy;
    private final String maxTrophy;
    private final String exp;
    private final String tripleMode;
    private final String soloMode;
    private final String duoMode;
    private final String rank25;
    private final String rank30;
    private final String rank35;
    private final String club;

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

        return Normalizer.normalize(input.name, Normalizer.Form.NFKC)
                .matches(".*(?i)" + this.name + ".*");
    }
}