package com.discord.MyBot;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class SlashCommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        System.out.println("event.getName() = " + event.getName());
        switch (event.getName()) {
            case "say":
                String content = event.getOption("content", OptionMapping::getAsString);
                event.reply(content).queue();
                break;
            case "leave":
                event.reply("I'm leaving the server now!")
                     .setEphemeral(true) // this message is only visible to the command user
                     .flatMap(m -> event.getGuild().leave()) // append a follow-up action using flatMap
                     .queue(); // enqueue both actions to run in sequence (send message -> leave guild)
                break;
            default:
                return;
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.printf("[%s] %#s: %s\n",
                event.getChannel(),
                event.getAuthor(),
                event.getMessage().getContentDisplay());
    }
}