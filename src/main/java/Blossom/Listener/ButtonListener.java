package Blossom.Listener;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ButtonListener extends ListenerAdapter {
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getButton().getId().equalsIgnoreCase("tag")) {
            /*
            Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
            String copyString = event.getButton().getLabel();

            StringSelection selection = new StringSelection(copyString);
            clipBoard.setContents(selection, null);

            event.getChannel().sendMessage(copyString).queue();
            */
        }
    }
}