package com.github.AleksandrSpencer.mtb.command;

import com.github.AleksandrSpencer.mtb.service.SendBotMessageService;
import com.github.AleksandrSpencer.mtb.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Statistics {@link Command}.
 */
public class StatCommand implements Command{
    private final TelegramUserService telegramUserService;
    private final SendBotMessageService sendBotMessageService;

    public final static String STAT_MESSAGE = "Spencer Telegram Bot использует %s человек.";

    //    @Autowired
    public StatCommand(SendBotMessageService sendBotMessageService,
                       TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        int activeUserCount = telegramUserService.findAllActiveUsers().size();
        sendBotMessageService
                .sendMessage(update.getMessage().getChatId().toString(),
                        String.format(STAT_MESSAGE, activeUserCount));
    }
}