package com.github.AleksandrSpencer.mtb.bot;

import com.github.AleksandrSpencer.mtb.command.CommandContainer;
import com.github.AleksandrSpencer.mtb.javarushclient.JavaRushGroupClient;
import com.github.AleksandrSpencer.mtb.service.GroupSubService;
import com.github.AleksandrSpencer.mtb.service.SendBotMessageServiceImpl;
import com.github.AleksandrSpencer.mtb.service.StatisticsService;
import com.github.AleksandrSpencer.mtb.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.github.AleksandrSpencer.mtb.command.CommandName.NO;


@Component
public class MyTelegramBot  extends TelegramLongPollingBot {
    public static String COMMAND_PREFIX = "/";
    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private  String token;

    private final CommandContainer commandContainer;
    @Autowired
    public MyTelegramBot (TelegramUserService telegramUserService,
                          JavaRushGroupClient groupClient,
                          GroupSubService groupSubService,
                          @Value("#{'${bot.admins}'.split(',')}") List<String> admins,
                          StatisticsService statisticsService) {
        this.commandContainer =
                new CommandContainer(new SendBotMessageServiceImpl(this),
                        telegramUserService, groupClient, groupSubService,
                        admins, statisticsService);
    }


    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            String username = update.getMessage().getFrom().getUserName();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier, username).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName(), username).execute(update);
            }
        }

    }

    @Override
    public String getBotUsername(){
        return username;
    }

    @Override
    public  String getBotToken(){
        return token;
    }
}