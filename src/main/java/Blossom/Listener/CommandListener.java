package Blossom.Listener;

import Blossom.Item.BrawlerItem;
import Blossom.Item.PlayerItem;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Command Listener
 * Date : 220701
 * Author : Laeti-Park, creator98@naver.com
 */
public class CommandListener extends ListenerAdapter {

    String tag = this.getClass().toString();

    String url = "http://clubblossom.site/blossom";
    BufferedReader player = new BufferedReader(new InputStreamReader((new URL(url + "/data/player")
            .openStream())));
    List<PlayerItem> playerItems = new ArrayList<>();
    BrawlerItem brawlerItem = new BrawlerItem();

    {
        try {
            // Player Data 불러오기
            while (true) {
                String item = player.readLine();
                if (item == null) {
                    break;
                }
                String[] info = item.split("\t");
                playerItems.add(new PlayerItem(info[0], info[1],
                        info[2], info[3], info[4], info[5],
                        info[6], info[7], info[8], info[9],
                        info[10], info[11]));
            }

            System.out.println("[" + tag + " Log] : "
                    + "Player Data/" + playerItems.get(0).getName());
            System.out.println("[" + tag + " Log] : "
                    + "Working Directory" + System.getProperty("user.dir"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CommandListener() throws IOException {
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String slashCommand = event.getName();
        EmbedBuilder embed = new EmbedBuilder();

        // 프로필 명령어 : 플레이어 프로필 정보 검색
        if (slashCommand.equals("프로필")) {
            OptionMapping statOption = event.getOption("닉네임");
            String statName = statOption.getAsString();

            int point = playerItems.indexOf(new PlayerItem(null, statName, null,
                    null, null, null, null, null,
                    null, null, null, null));
            File clubIcon = new File("./img/Club/Blossom_Club_Color_"
                    + (playerItems.get(point).getClub().equals("#C2RCY8C2") ? "A.png" : "B.png"));
            String clubColor = playerItems.get(point).getClub().equals("#C2RCY8C2") ? "#89CEEB" : "#FCC9B9";

            System.out.println("[" + tag + " Log] : " + "입력 닉네임/" + statName);
            System.out.println("[" + tag + " Log] : " + "검색 닉네임/" + playerItems.get(point).getName());

            embed.setThumbnail("attachment://BlossomClub.png");
            embed.setTitle("프로필 닉네임\n" + playerItems.get(point).getName(),
                    "https://brawlstats.com/profile/"
                            + playerItems.get(point).getTag().replace("#", ""));
            embed.addField("플레이어 태그", playerItems.get(point).getTag(), false);
            embed.addField("현재 트로피", playerItems.get(point).getNowTrophy(), true);
            embed.addField("최대 트로피", playerItems.get(point).getMaxTrophy(), true);
            embed.addBlankField(true);
            embed.addField("3대3 승리", playerItems.get(point).getTripleMode(), true);
            embed.addField("듀오 승리", playerItems.get(point).getDuoMode(), true);
            embed.addField("솔로 승리", playerItems.get(point).getSoloMode(), true);
            embed.addField("25랭크", playerItems.get(point).getRank25() + "개", true);
            embed.addField("30랭크", playerItems.get(point).getRank30() + "개", true);
            embed.addField("35랭크", playerItems.get(point).getRank35() + "개", true);

            embed.setColor(Color.decode(clubColor));
            event.replyEmbeds(embed.build())
                    .addFile(clubIcon, "BlossomClub.png").queue();
        }

        // 랜덤 브롤러 : 랜덤 브롤러 선택
        if (slashCommand.equals("돌림판")) {
            int randomNum = (int) (Math.random() * 57 + 1);
            File randomImage = new File("./img/Brawler/Brawler_" + randomNum + ".png");

            System.out.println("[" + tag + " Log] : " + "돌림판 번호/" + randomNum);

            embed.setTitle("\uD83D\uDEDE돌려돌려 돌림판\uD83D\uDEDE");
            embed.setThumbnail("attachment://Brawler_Random.png");
            embed.addField("돌림판의 브롤러는?!",
                    brawlerItem.brawler[randomNum - 1], false);
            event.replyEmbeds(embed.build())
                    .addFile(randomImage, "Brawler_Random.png").queue();
        }

        // 문의 : 오류 및 기능 추가 문의
        if (slashCommand.equals("문의")) {
            File botLogo = new File("./img/Blossom_Discord_Logo.png");

            embed.setThumbnail("attachment://Blossom_Discord_Logo.png");
            embed.addField("카카오톡 문의", "https://open.kakao.com/me/Laeti_Cre\n" +
                    "오류가 발생하거나 추가하고 싶은 기능이 있으면 문의주세요!", false);
            embed.setFooter("블라썸 봇 개발 문의", "attachment://Blossom_Discord_Logo.png");

            embed.setColor(Color.decode("#E56AA6"));
            event.replyEmbeds(embed.build())
                    .addFile(botLogo, "Blossom_Discord_Logo.png").queue();
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("프로필", "플레이어 프로필 정보 검색")
                .addOption(OptionType.STRING, "닉네임", "인게임 닉네임 일부를 입력"));
        commandData.add(Commands.slash("문의", "오류 및 기능 추가 문의"));
        commandData.add(Commands.slash("돌림판", "돌려돌려 돌림판\uD83D\uDE35\u200D\uD83D\uDCAB"));

        if (event.getGuild().getIdLong() == 656733677270597662L) {
            event.getGuild().updateCommands().addCommands(commandData).queue();
        }
    }
}
