package com.discord.Settings;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "discord.bot")
public class DiscordSettings {
    private String token;
}
