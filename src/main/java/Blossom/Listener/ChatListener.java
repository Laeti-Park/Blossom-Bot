package Blossom.Listener;

import Blossom.Item.PlayerItem;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

/**
 * Discord Message Listener
 * <p>
 * Date : 220529
 * Author : Laeti-Park, creator98@naver.com
 */
public class ChatListener extends ListenerAdapter {

    String url = "http://clubblossom.site/blossom";
    BufferedReader player;
    List<PlayerItem> playerItems = new ArrayList<>();

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 봇이 Message를 받을 경우 Event
     *
     * @param event : 받은 Message 정보
     */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);

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

        // 명령어 확인
        if (event.getMessage().getContentRaw().equals("/레티")) {
            event.getChannel().sendMessage("레티 똑똑 " + event.getAuthor().getAsMention()).queue();
        }

        if (event.getMessage().getContentRaw().charAt(0) == '/') {
            String[] args = event.getMessage().getContentRaw().substring(1).split(" ");

            if (args.length <= 0) return;
            if (args[0].equalsIgnoreCase("player") || args[0].equalsIgnoreCase("p")) {


                if (args.length < 2) return; // 모든 플레이어 리스트
                else {
                    int point = playerItems.indexOf(new PlayerItem(null, args[1], null,
                            null, null, null, null, null,
                            null, null, null, null));

                    System.out.println(args[1]);
                    System.out.println(playerItems.get(point).getName());
                    event.getChannel().sendMessage("와 샌즈! : " + playerItems.get(point).getName()).queue();
                }
            }
        }
    }
}