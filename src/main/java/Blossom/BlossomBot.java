package Blossom;

import Blossom.Listener.ButtonListener;
import Blossom.Listener.ChatListener;
import Blossom.Listener.CommandListener;
import com.oracle.bmc.Region;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;

public class BlossomBot implements EventListener {

    String tag = this.getClass().toString();

    // JDA: Discord API를 자바에서 사용할 수 있는 라이브러리
    public static JDA jda;

    public static void main(String[] args) throws LoginException, IOException {

        String configurationFilePath = "~/.oci/config";
        String profile = "DEFAULT";

        AuthenticationDetailsProvider provider =
                new ConfigFileAuthenticationDetailsProvider(configurationFilePath, profile);

        ObjectStorage client = new ObjectStorageClient(provider);
        client.setRegion(Region.AP_CHUNCHEON_1);

        // 보안을 위해 토큰 파일로 분리
        BufferedReader tokenFile = new BufferedReader(new FileReader("./token"));

        String token = tokenFile.readLine();
        jda = JDABuilder.createDefault(token).addEventListeners(new Blossom.BlossomBot()).build();
        jda.addEventListener(new ChatListener(), new CommandListener(), new ButtonListener());
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent)
            System.out.println("[" + tag + " Log] : " + "Blossom-Bot Start");
    }
}