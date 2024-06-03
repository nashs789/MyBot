package com.discord.MyBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.EnumSet;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

@SpringBootApplication
public class MyBotApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyBotApplication.class, args);
		JDA jda = JDABuilder.createLight("",
						EnumSet.of(GatewayIntent.GUILD_MESSAGES,
						GatewayIntent.MESSAGE_CONTENT,
						GatewayIntent.GUILD_MEMBERS,
						GatewayIntent.GUILD_VOICE_STATES,
						GatewayIntent.GUILD_PRESENCES)
				)
				.addEventListeners(new SlashCommandListener())
				.build();

		// Register your commands to make them visible globally on Discord:

		CommandListUpdateAction commands = jda.updateCommands();

		// Add all your commands on this action instance
		commands.addCommands(
				Commands.slash("say", "Makes the bot say what you tell it to")
						.addOption(STRING, "content", "What the bot should say", true), // Accepting a user input
				Commands.slash("leave", "Makes the bot leave the server")
						.setGuildOnly(true) // this doesn't make sense in DMs
						.setDefaultPermissions(DefaultMemberPermissions.DISABLED) // only admins should be able to use this command.
		);

		// Then finally send your commands to discord using the API
		commands.queue();
	}
}
