package Blossom.Listener;

import Blossom.Item.PlayerItem;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Discord Message Listener
 * Date : 220529
 * Author : Laeti-Park, creator98@naver.com
 */
public class ChatListener extends ListenerAdapter {

    String url = "http://clubblossom.site/blossom";
    BufferedReader player;
    List<PlayerItem> playerItems = new ArrayList<>();
    File discordLogo = new File("./img/Blossom_Discord_Logo.png");

    {
        try {
            player = new BufferedReader(new InputStreamReader((new URL(url + "/data/player").openStream())));
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

            System.out.println(playerItems.get(0).getName());
            System.out.println(discordLogo.exists());
            System.out.println(System.getProperty("user.dir"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
    }

    /**
     * 봇이 Message를 받을 경우 Event
     *
     * @param event : 받은 Message 정보
     */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        EmbedBuilder embed = new EmbedBuilder();

        // 채팅 채널 메시지일 경우와 DM일 경우 메시지 로그 출력
        if (event.isFromType(ChannelType.TEXT)) {
            System.out.printf("[Message_Log] %s\\%s\\%s : %s\n", event.getGuild().getName(),
                    event.getChannel().getName(), event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());
        } else {
            System.out.printf("[Message_Log] DM\\%s : %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());
        }

        // 메시지가 봇일 경우
        if (event.getAuthor().isBot()) {
            return;
        }

        // 개발 문의
        if (event.getMessage().getContentRaw().equals("/레티") || event.getMessage().getContentRaw().equals("/문의")) {

            embed.setThumbnail("attachment://Blossom_Discord_Logo.png");
            embed.addField("카카오톡 문의", "https://open.kakao.com/me/Laeti_Cre\n" +
                    "오류가 발생하거나 추가하고 싶은 기능이 있으면 문의주세요!", false);
            embed.setFooter("블라썸 봇 개발 문의", "attachment://Blossom_Discord_Logo.png");

            embed.setColor(Color.decode("#E56AA6"));
            event.getChannel().sendMessageEmbeds(embed.build())
                    .addFile(discordLogo, "Blossom_Discord_Logo.png").queue();
        }

        if (event.getMessage().getContentRaw().charAt(0) == '/') {
            String[] args = event.getMessage().getContentRaw().substring(1).split(" ");

            if (args.length <= 0) return;

            /*
            stats(s) 명령어 : 플레이어 프로필 정보 검색
            */
            if (args[0].equalsIgnoreCase("stats") || args[0].equalsIgnoreCase("s")) {

                if (args.length < 2) {
                    System.out.println("Hi");
                } else {
                    int point = playerItems.indexOf(new PlayerItem(null, args[1], null,
                            null, null, null, null, null,
                            null, null, null, null));

                    System.out.println(args[1]);
                    System.out.println(playerItems.get(point).getName());

                    embed.setTitle(playerItems.get(point).getName() + "님의 프로필");
                    embed.addField("플레이어 태그", playerItems.get(point).getTag(), false);
                    embed.addField("현재 트로피", playerItems.get(point).getNowTrophy(), false);
                    embed.addField("최대 트로피", playerItems.get(point).getMaxTrophy(), false);
                    embed.addField("3대3 승리", playerItems.get(point).getTripleMode(), false);
                    embed.addField("듀오 승리", playerItems.get(point).getDuoMode(), false);
                    embed.addField("솔로 승리", playerItems.get(point).getSoloMode(), false);
                    embed.addField("25랭크", playerItems.get(point).getRank25() + "개", false);
                    embed.addField("30랭크", playerItems.get(point).getRank30() + "개", false);
                    embed.addField("35랭크", playerItems.get(point).getRank35() + "개", false);

                    if (playerItems.get(point).getClub().equals("#C2RCY8C2")) {
                        embed.setThumbnail("attachment://BlossomA.png");
                        embed.setColor(Color.decode("#E56AA6"));
                        event.getChannel().sendMessageEmbeds(embed.build())
                                .addFile(discordLogo, "BlossomA.png").queue();
                    } else {
                        embed.setThumbnail("attachment://BlossomB.png");
                        embed.setColor(Color.decode("#5A63AD"));
                        event.getChannel().sendMessageEmbeds(embed.build())
                                .addFile(discordLogo, "BlossomB.png").queue();
                    }
                }
            }
        }
    }
}