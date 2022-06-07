package Blossom;

import Blossom.Listener.ChatListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BlossomBot implements EventListener {

    // JDA: Discord API를 자바에서 사용할 수 있는 라이브러리
    public static JDA jda;

    public static void main(String[] args) throws LoginException, IOException {

        BufferedReader tokenFile = new BufferedReader(new FileReader("./token")); // 보안을 위해 토큰 파일로 분리

        String token = tokenFile.readLine();
        jda = JDABuilder.createDefault(token).addEventListeners(new BlossomBot()).build();
        jda.addEventListener(new ChatListener());
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent)
            System.out.println("Hello World!");
    }
}