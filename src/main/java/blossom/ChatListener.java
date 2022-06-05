package blossom;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Discord Message Listener
 * <p>
 * Date : 220529
 * Author : Laeti-Park, creator98@naver.com
 */
public class ChatListener extends ListenerAdapter {

    String url = "http://clubblossom.site/blossom";
    BufferedReader player;
    List<String> playerList = new ArrayList<>();

    {
        try {
            player = new BufferedReader(new InputStreamReader((new URL(url + "/data/player").openStream())));
            while (true) {
                String item = player.readLine();
                if (item == null) {
                    break;
                }
                playerList.add(item);
            }
            System.out.println(playerList.get(0));
            System.out.println();
            for (String data : playerList) {
                System.out.println(data);
            }
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


    }
}