package com.github.AleksandrSpencer.mtb.command;

import com.github.AleksandrSpencer.mtb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
/**
 * Unknown {@link Command}.
 */
public class UnknownCommand implements  Command{

    public static final String UNKNOWN_MESSAGE = "Не понимаю Вас \uD83D\uDE1F, напишите /help чтобы узнать что я понимаю.";

    private final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
    }
}