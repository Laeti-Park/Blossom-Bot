package blossom;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * Discord Message Listener
 *
 * Date : 220529
 * Author : Laeti-Park, creator98@naver.com
 */
public class ChatListener extends ListenerAdapter {

    /**
     * 봇이 Message를 받을 경우 Event
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
        if(event.getAuthor().isBot()) {
            return;
        }

        // 명령어 확인
        if(event.getMessage().getContentRaw().equals("/레티")) {
            event.getChannel().sendMessage("레티 똑똑 " + event.getAuthor().getAsMention()).queue();
        }
    }
}